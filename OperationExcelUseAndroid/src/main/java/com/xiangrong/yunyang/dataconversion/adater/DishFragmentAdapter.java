package com.xiangrong.yunyang.dataconversion.adater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silencedut.expandablelayout.ExpandableLayout;
import com.xiangrong.yunyang.dataconversion.R;
import com.xiangrong.yunyang.dataconversion.entity.School;

import java.util.HashSet;
import java.util.List;

/**
 * 作者    yunyang
 * 时间    2019/1/7 9:58
 * 文件    DataConversion
 * 描述   盘碎片的RecyclerView的适配器
 */
public class DishFragmentAdapter extends RecyclerView.Adapter<DishFragmentAdapter.DishHolder> {

    private LayoutInflater mInflater;
    private HashSet<Integer> mExpandedPositionSet = new HashSet<>();
    private List<School> mSchoolList;

    public DishFragmentAdapter(Context context, List<School> schools) {
        this.mInflater = LayoutInflater.from(context);
        mSchoolList = schools;
    }

    @Override
    public DishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = mInflater.inflate(R.layout.item_recy_fragment_dish, parent, false);
        return new DishHolder(item);
    }

    @Override
    public void onBindViewHolder(DishHolder holder, int position) {

        holder.mTextViewNumber.setText(mSchoolList.get(position).getAssetNumber());
        holder.mTextViewName.setText(mSchoolList.get(position).getAssetName());
        holder.mTextViewDate.setText(mSchoolList.get(position).getDateOfFinancialEntry());
        holder.mTextViewMoney.setText(mSchoolList.get(position).getNetBookValue());

        holder.mTextViewZero.setText(mSchoolList.get(position).getAssetNumber());
        holder.mTextViewOne.setText(mSchoolList.get(position).getAssetName());
        holder.mTextViewTwo.setText(mSchoolList.get(position).getAssetClassification());
//        holder.mTextViewThree.setText(mSchoolList.get(position).getNationalStandardClassification());
        holder.mTextViewFour.setText(mSchoolList.get(position).getActualNumberOf());
//        holder.mTextViewFive.setText(mSchoolList.get(position).getActualValue());
//        holder.mTextViewSix.setText(mSchoolList.get(position).getActualAccumulatedDepreciation());
//        holder.mTextViewSeven.setText(mSchoolList.get(position).getInventoryResults());
//        holder.mTextViewEight.setText(mSchoolList.get(position).getUseStatus());
//        holder.mTextViewNine.setText(mSchoolList.get(position).getSerialNumber());
        holder.mTextViewTen.setText(mSchoolList.get(position).getPhysicalCountQuantity());
        holder.mTextViewZeroZero.setText(mSchoolList.get(position).getBookValue());
//        holder.mTextViewOneOne.setText(mSchoolList.get(position).getBookDepreciation());
//        holder.mTextViewTwoTwo.setText(mSchoolList.get(position).getNetBookValue());
//        holder.mTextViewThreeThree.setText(mSchoolList.get(position).getGainingMethod());
//        holder.mTextViewFourFour.setText(mSchoolList.get(position).getSpecificationsAndModels());
//        holder.mTextViewFiveFive.setText(mSchoolList.get(position).getUnitOfMeasurement());
//        holder.mTextViewSixSix.setText(mSchoolList.get(position).getDateOfAcquisition());
        holder.mTextViewSevenSeven.setText(mSchoolList.get(position).getDateOfFinancialEntry());
//        holder.mTextViewEightEight.setText(mSchoolList.get(position).getTypeOfValue());
        holder.mTextViewNineNine.setText(mSchoolList.get(position).getStoragePlace());
        holder.mTextViewTenTen.setText(mSchoolList.get(position).getUserDepartment());
        holder.mTextViewZeroZeroZero.setText(mSchoolList.get(position).getUser());
//        holder.mTextViewOneOneOne.setText(mSchoolList.get(position).getOriginalAssetNumber());
        holder.mTextViewTwoTwoTwo.setText(mSchoolList.get(position).getRemark());
        holder.updateItem(position);
    }

    @Override
    public int getItemCount() {
        return mSchoolList.size();
    }

    class DishHolder extends RecyclerView.ViewHolder {

        private ExpandableLayout expandableLayout;

        private TextView mTextViewNumber;
        private TextView mTextViewName;
        private TextView mTextViewDate;
        private TextView mTextViewMoney;

        private TextView mTextViewZero;
        private TextView mTextViewOne;
        private TextView mTextViewTwo;
//        private TextView mTextViewThree;
        private TextView mTextViewFour;
//        private TextView mTextViewFive;
//        private TextView mTextViewSix;
//        private TextView mTextViewSeven;
//        private TextView mTextViewEight;
//        private TextView mTextViewNine;
        private TextView mTextViewTen;
        private TextView mTextViewZeroZero;
//        private TextView mTextViewOneOne;
//        private TextView mTextViewTwoTwo;
//        private TextView mTextViewThreeThree;
//        private TextView mTextViewFourFour;
//        private TextView mTextViewFiveFive;
//        private TextView mTextViewSixSix;
        private TextView mTextViewSevenSeven;
//        private TextView mTextViewEightEight;
        private TextView mTextViewNineNine;
        private TextView mTextViewTenTen;
        private TextView mTextViewZeroZeroZero;
//        private TextView mTextViewOneOneOne;
        private TextView mTextViewTwoTwoTwo;

        private DishHolder(final View itemView) {
            super(itemView);

            expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);

            mTextViewNumber = (TextView) itemView.findViewById(R.id.text_string_number);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_string_name);
            mTextViewDate = (TextView) itemView.findViewById(R.id.text_string_date);
            mTextViewMoney = (TextView) itemView.findViewById(R.id.text_string_money);

            mTextViewZero = (TextView) itemView.findViewById(R.id.text_string_zero);
            mTextViewOne = (TextView) itemView.findViewById(R.id.text_string_one);
            mTextViewTwo = (TextView) itemView.findViewById(R.id.text_string_two);
//            mTextViewThree = (TextView) itemView.findViewById(R.id.text_string_three);
            mTextViewFour = (TextView) itemView.findViewById(R.id.text_string_four);
//            mTextViewFive = (TextView) itemView.findViewById(R.id.text_string_five);
//            mTextViewSix = (TextView) itemView.findViewById(R.id.text_string_six);
//            mTextViewSeven = (TextView) itemView.findViewById(R.id.text_string_seven);
//            mTextViewEight = (TextView) itemView.findViewById(R.id.text_string_eight);
//            mTextViewNine = (TextView) itemView.findViewById(R.id.text_string_nine);
            mTextViewTen = (TextView) itemView.findViewById(R.id.text_string_ten);
            mTextViewZeroZero = (TextView) itemView.findViewById(R.id.text_string_zero_zero);
//            mTextViewOneOne = (TextView) itemView.findViewById(R.id.text_string_one_one);
//            mTextViewTwoTwo = (TextView) itemView.findViewById(R.id.text_string_two_two);
//            mTextViewThreeThree = (TextView) itemView.findViewById(R.id.text_string_three_three);
//            mTextViewFourFour = (TextView) itemView.findViewById(R.id.text_string_four_four);
//            mTextViewFiveFive = (TextView) itemView.findViewById(R.id.text_string_five_five);
//            mTextViewSixSix = (TextView) itemView.findViewById(R.id.text_string_six_six);
            mTextViewSevenSeven = (TextView) itemView.findViewById(R.id.text_string_seven_seven);
//            mTextViewEightEight = (TextView) itemView.findViewById(R.id.text_string_eight_eight);
            mTextViewNineNine = (TextView) itemView.findViewById(R.id.text_string_nine_nine);
            mTextViewTenTen = (TextView) itemView.findViewById(R.id.text_string_ten_ten);
            mTextViewZeroZeroZero = (TextView) itemView.findViewById(R.id.text_string_zero_zero_zero);
//            mTextViewOneOneOne = (TextView) itemView.findViewById(R.id.text_string_one_one_one);
            mTextViewTwoTwoTwo = (TextView) itemView.findViewById(R.id.text_string_two_two_two);
        }

        private void updateItem(final int position) {
            expandableLayout.setOnExpandListener(new ExpandableLayout.OnExpandListener() {
                @Override
                public void onExpand(boolean expanded) {
                    registerExpand(position);
                }
            });
            expandableLayout.setExpand(mExpandedPositionSet.contains(position));

        }
    }

    private void registerExpand(int position) {
        if (mExpandedPositionSet.contains(position)) {
            removeExpand(position);
        } else {
            addExpand(position);
        }
    }

    private void removeExpand(int position) {
        mExpandedPositionSet.remove(position);
    }

    private void addExpand(int position) {
        mExpandedPositionSet.add(position);
    }

    public void setDataNotify(List<School> schools) {
        mSchoolList.clear();
        mSchoolList.addAll(schools);
        notifyDataSetChanged();
    }

}
