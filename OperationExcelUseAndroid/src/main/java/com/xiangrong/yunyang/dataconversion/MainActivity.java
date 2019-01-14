package com.xiangrong.yunyang.dataconversion;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.xiangrong.yunyang.dataconversion.entity.CurrentFileName;
import com.xiangrong.yunyang.dataconversion.entity.ExpandMessage;
import com.xiangrong.yunyang.dataconversion.entity.School;
import com.xiangrong.yunyang.dataconversion.entity.UnderWayFileLists;
import com.xiangrong.yunyang.dataconversion.utils.ExcelUtils;
import com.xiangrong.yunyang.dataconversion.utils.FileUtil;
import com.xiangrong.yunyang.dataconversion.utils.StrUtil;
import com.xiangrong.yunyang.dataconversion.view.dialog.LoadingDialog;
import com.xiangrong.yunyang.dataconversion.view.popup.SlideFromBottomPopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Excel表格的表头
    private String[] columnTitle = {"资产编号", "资产名称", "资产分类", "国标分类", "实有数量", "实有原值", "实有累计折旧", "盘点结果", "使用状况", "产品序列号",
            "账面数量", "账面价值", "账面累计折旧", "账面净值", "取得方式", "规格型号", "计量单位", "取得日期", "财务入账日期", "价值类型", "存放地点", "使用部门", "使用人",
            "原资产编号", "备注"};

    private AppCompatButton btn_import;
    private AppCompatButton btn_export;
    private AppCompatButton btn_delete;
    private AppCompatButton btn_query;

    // 加载中的Dialog
    private LoadingDialog mLoadingDialog;

    private List<School> mSchoolListToDb;
    private List<School> mDbToSchoolList;

    private List<School> mSchoolsExcelName;
    private Set<String> mHashSet;

    private File mFileDir;
    private Vector<File> mSubFileVector;
    // Spinner当前选中数据表
    private File currentFile;

    private AppCompatTextView select_text_db_file_excel;

    private SlideFromBottomPopup mSlideFromBottomPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFileDir();
        initData();
    }

    /**
     * 创建特定文件夹以存放Excel表
     */
    private void initFileDir() {
        mFileDir = FileUtil.createDir("YunYangData");
    }

    /**
     * 初始化数据库
     */
    private void initData() {

        mSchoolListToDb = new ArrayList<>();
        mDbToSchoolList = new ArrayList<>();
        LitePal.getDatabase();

        mSchoolsExcelName = new ArrayList<>();
        mHashSet = new HashSet<>();
        mSubFileVector = new Vector<File>();

        mSlideFromBottomPopup =
                new SlideFromBottomPopup(MainActivity.this, mFileDir.getAbsolutePath());
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn_import = (AppCompatButton) findViewById(R.id.btn_import);
        btn_export = (AppCompatButton) findViewById(R.id.btn_export);
        btn_delete = (AppCompatButton) findViewById(R.id.btn_delete);
        btn_query = (AppCompatButton) findViewById(R.id.btn_query);

        btn_import.setOnClickListener(this);
        btn_export.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_query.setOnClickListener(this);

        select_text_db_file_excel = (AppCompatTextView) findViewById(R.id.select_text_db_file_excel);
        select_text_db_file_excel.setOnClickListener(this);

        if (select_text_db_file_excel.getText().toString().equals("请您选择Excel文件")) {
            checkFirstBtnEnabled(false);
        }
    }

    private void checkBtnEnabled(boolean flag) {
        btn_import.setEnabled(flag);
        btn_export.setEnabled(flag);
        btn_query.setEnabled(flag);
        btn_delete.setEnabled(flag);
    }

    private void checkBtnEnabledFlag(boolean flag) {
        btn_import.setEnabled(flag);
    }

    private void checkFirstBtnEnabled(boolean flag) {
        btn_import.setEnabled(flag);
        btn_export.setEnabled(flag);
        btn_query.setEnabled(flag);
        btn_delete.setEnabled(flag);
    }

    private List<School> shenqi;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_import:
                checkCurrentFileNameFromDb();
                break;
            case R.id.btn_export:
                mLoadingDialog = new LoadingDialog(this, "数据正在导出中...");
                startLoading();
                exportCreateExcelFromJxl();
                break;
            case R.id.btn_delete:
                if (currentFile != null) {
                    deleteDbFromDataSheet(true);
                } else {
                    Toast.makeText(this, "请选中要从本地数据库中删除之前导入的Excel文件名称", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_query:
                Intent intentQueryData = new Intent(MainActivity.this, QueryDataActivity.class);
                startActivity(intentQueryData);
                break;
            case R.id.select_text_db_file_excel:
                mSlideFromBottomPopup.newPopupBottomShow();
                break;
            default:
                break;
        }
    }

    /**
     * 检查数据库中是否存在当前Excel表名，如果存在，导入时是否覆盖导入。
     */
    private void checkCurrentFileNameFromDb() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                final int count = LitePal
                        .where("ownershipDataSheet = ?", currentFile.getName())
                        .count(School.class);
                return count;
            }

            @Override
            protected void onPostExecute(Integer aVoid) {
                super.onPostExecute(aVoid);
                mLoadingDialog = new LoadingDialog(MainActivity.this, "数据正在导入中...");
                if (aVoid > 0) {
                    new QMUIDialog.MessageDialogBuilder(MainActivity.this)
                            .setTitle("提示")
                            .setMessage("本地数据库中已经存在 "
                                    + StrUtil.getFileNameNoEx(currentFile.getName())
                                    + " Excel表，是否覆盖导入？")
                            .addAction("取消", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            })
                            .addAction("确定", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    if (currentFile != null) {
                                        deleteDbFromDataSheet(false);
                                        readExcelToDB();
                                    } else {
                                        Toast.makeText(MainActivity.this, "请选中要从本地数据库中覆盖之前导入的Excel文件名称", Toast.LENGTH_LONG).show();
                                    }
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    readExcelToDB();
                }
            }
        }.execute();

    }

    /**
     * 依据 所属数据表 列进行数据删除
     */
    private void deleteDbFromDataSheet(final boolean flag) {
        startLoading();
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                try {
                    LitePal
                            .deleteAll(
                                    School.class, "ownershipDataSheet = ?",
                                    currentFile.getName());
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override
            protected void onPostExecute(Integer aVoid) {
                super.onPostExecute(aVoid);
                if (aVoid == 1) {
                    if (flag) {
                        Toast.makeText(MainActivity.this, "从本地数据库中删除 " + currentFile.getName() + " 成功", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (flag) {
                        stopLoading();
                        Toast.makeText(MainActivity.this, "从本地数据库中删除 " + currentFile.getName() + " 失败", Toast.LENGTH_LONG).show();
                    }
                }
                stopLoading();
            }
        }.execute();
    }

    /**
     * 导出 创建Excel表格，使用Jxl
     */
    private void exportCreateExcelFromJxl() {
        new AsyncTask<String, Void, Integer>() {

            @Override
            protected Integer doInBackground(String... params) {
                try {
                    // 创建Excel表格
                    ExcelUtils
                            .initExcel(FileUtil
                                    .createFile(currentFile.getName())
                                    .getAbsolutePath(), columnTitle);
                    // 创建Sheet表，并写入数据
                    mDbToSchoolList.clear();
                    /*
                        导出之前，删除原表，创建新表
                        编写导出LitePal语句，导出Excel表格依据26列（所属数据表进行导出）

                     */
                    mDbToSchoolList = LitePal.findAll(School.class);
                    ExcelUtils.writeSchoolListToExcel(mDbToSchoolList, FileUtil
                            .createFile(currentFile.getName())
                            .getAbsolutePath(), MainActivity.this);
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override
            protected void onPostExecute(Integer aVoid) {
                super.onPostExecute(aVoid);
                if (aVoid == 1) {
                    Toast.makeText(MainActivity.this, "导出成功！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "导出失败！", Toast.LENGTH_LONG).show();
                }
                stopLoading();
            }
        }.execute();
    }

    /**
     * 结束加载Dialog
     */
    private void stopLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 开始加载Dialog
     */
    private void startLoading() {
        if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        stopLoading();
    }

    /**
     * 读取excel数据到数据库里（LitePal）
     */
    private void readExcelToDB() {
        startLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            /*
                Android 读取Assets 下的Excel文件
             */
//            InputStream is = context.getAssets().open("康复所.xls");
//            Workbook book = Workbook.getWorkbook(is);

                    Workbook book = Workbook.getWorkbook(currentFile);
                    // 获取表页数
                    final int bookPage = book.getNumberOfSheets();
                    Log.e("bookPage", "bookPage = " + bookPage);
                    // 获得第一个工作表对象
                    Sheet sheet = book.getSheet(0);
                    int Rows = sheet.getRows();
                    School schoolData = null;
                    for (int i = 1; i < Rows; ++i) {
                        String assetNumber = (sheet.getCell(0, i)).getContents();
                        String assetName = (sheet.getCell(1, i)).getContents();
                        String assetClassification = (sheet.getCell(2, i)).getContents();
                        String nationalStandardClassification = (sheet.getCell(3, i)).getContents();
                        String actualNumberOf = (sheet.getCell(4, i)).getContents();
                        String actualValue = (sheet.getCell(5, i)).getContents();
                        String actualAccumulatedDepreciation = (sheet.getCell(6, i)).getContents();
                        String inventoryResults = (sheet.getCell(7, i)).getContents();
                        String useStatus = (sheet.getCell(8, i)).getContents();
                        String serialNumber = (sheet.getCell(9, i)).getContents();
                        String physicalCountQuantity = (sheet.getCell(10, i)).getContents();
                        String bookValue = (sheet.getCell(11, i)).getContents();
                        String bookDepreciation = (sheet.getCell(12, i)).getContents();
                        String netBookValue = (sheet.getCell(13, i)).getContents();
                        String gainingMethod = (sheet.getCell(14, i)).getContents();
                        String specificationsAndModels = (sheet.getCell(15, i)).getContents();
                        String unitOfMeasurement = (sheet.getCell(16, i)).getContents();
                        String dateOfAcquisition = (sheet.getCell(17, i)).getContents();
                        String dateOfFinancialEntry = (sheet.getCell(18, i)).getContents();
                        String typeOfValue = (sheet.getCell(19, i)).getContents();
                        String storagePlace = (sheet.getCell(20, i)).getContents();
                        String userDepartment = (sheet.getCell(21, i)).getContents();
                        String user = (sheet.getCell(22, i)).getContents();
                        String originalAssetNumber = (sheet.getCell(23, i)).getContents();
                        String remark = (sheet.getCell(24, i)).getContents();
                /*
                    25是盘点数量
                    导入的时候把实有数量给盘点数量
                    导出的时候把盘点数量给实有数量
                 */
                        // 导入Db——这里先直接赋值为0
                        String inventoryData = "0";
                        // 26是所属数据表
//                String ownershipDataSheet = "康复所";
                        String ownershipDataSheet = "";
                        if (currentFile != null) {
                            ownershipDataSheet = currentFile.getName();
                        }
                        schoolData = new School(assetNumber, assetName, assetClassification,
                                nationalStandardClassification, actualNumberOf, actualValue,
                                actualAccumulatedDepreciation, inventoryResults, useStatus,
                                serialNumber, physicalCountQuantity, bookValue,
                                bookDepreciation, netBookValue, gainingMethod, specificationsAndModels,
                                unitOfMeasurement, dateOfAcquisition, dateOfFinancialEntry,
                                typeOfValue, storagePlace, userDepartment, user, originalAssetNumber
                                , remark, inventoryData, ownershipDataSheet);
                        mSchoolListToDb.add(schoolData);
                    }
                    book.close();
                    saveDB();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopLoading();
                            checkBtnEnabled(true);
                            Toast.makeText(MainActivity.this, "导入成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopLoading();
                            Toast.makeText(MainActivity.this, "导入失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 数据保存到数据库中并且去空行
     */
    private void saveDB() {
        if (mSchoolListToDb != null) {
            LitePal.saveAll(mSchoolListToDb);
            // 去空行
            LitePal.deleteAll(School.class, "assetNumber = ?", "");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ExpandMessage expandMessage) {
        final String fileName = expandMessage.getFileName();
        select_text_db_file_excel.setText(fileName);
        if (!select_text_db_file_excel.getText().toString().equals("请您选择Excel文件")) {
            checkBtnEnabledFlag(true);
        }
        EventBus
                .getDefault()
                .postSticky(new CurrentFileName(fileName));
        for (File file :
                expandMessage.getSubFileVector()) {
            if (file.getName().equals(fileName)) {
                currentFile = file;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFileListsEvent(UnderWayFileLists fileLists) {
        for (String str :
                fileLists.getSetFiles()) {
            if (!str.equals(fileLists.getFileName())) {
                checkBtnEnabled(false);
                checkBtnEnabledFlag(true);
            } else {
                checkBtnEnabled(true);
                return;
            }
        }
    }

}
