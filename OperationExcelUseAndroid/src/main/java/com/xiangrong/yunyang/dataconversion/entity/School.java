package com.xiangrong.yunyang.dataconversion.entity;

import org.litepal.crud.LitePalSupport;

/**
 * 作者    yunyang
 * 时间    2019/1/2 11:39
 * 文件    DataConversion
 * 描述   陝西老年大学资料
 */
public class School extends LitePalSupport {

    // 资产编号
    private String assetNumber;
    // 资产名称
    private String assetName;
    // 资产分类
    private String assetClassification;
    // 国标分类
    private String nationalStandardClassification;
    // 实有数量
    private String actualNumberOf;
    // 实有原值
    private String actualValue;
    // 实有累计折旧
    private String actualAccumulatedDepreciation;
    // 盘点结果
    private String inventoryResults;
    // 使用情况
    private String useStatus;
    // 产品序列号
    private String serialNumber;
    // 账面数量
    private String physicalCountQuantity;
    // 账面价值
    private String bookValue;
    // 账面累计折旧
    private String bookDepreciation;
    // 账面净值
    private String netBookValue;
    // 取值方式
    private String gainingMethod;
    // 规格型号
    private String specificationsAndModels;
    // 计量单位
    private String unitOfMeasurement;
    // 取得日期
    private String dateOfAcquisition;
    // 财务入账日期
    private String dateOfFinancialEntry;
    // 价值类型
    private String typeOfValue;
    // 存放地点
    private String storagePlace;
    // 使用部门
    private String userDepartment;
    // 使用人
    private String user;
    // 原资产编号
    private String originalAssetNumber;
    // 备注
    private String remark;
    // 盘点数量
    private String inventoryData;
    // 所属数据表
    private String ownershipDataSheet;

    public School(String assetNumber, String assetName, String assetClassification,
                  String nationalStandardClassification, String actualNumberOf,
                  String actualValue, String actualAccumulatedDepreciation, String inventoryResults,
                  String useStatus, String serialNumber, String physicalCountQuantity,
                  String bookValue, String bookDepreciation, String netBookValue,
                  String gainingMethod, String specificationsAndModels, String unitOfMeasurement,
                  String dateOfAcquisition, String dateOfFinancialEntry, String typeOfValue,
                  String storagePlace, String userDepartment, String user,
                  String originalAssetNumber, String remark, String inventoryData, String ownershipDataSheet) {
        this.assetNumber = assetNumber;
        this.assetName = assetName;
        this.assetClassification = assetClassification;
        this.nationalStandardClassification = nationalStandardClassification;
        this.actualNumberOf = actualNumberOf;
        this.actualValue = actualValue;
        this.actualAccumulatedDepreciation = actualAccumulatedDepreciation;
        this.inventoryResults = inventoryResults;
        this.useStatus = useStatus;
        this.serialNumber = serialNumber;
        this.physicalCountQuantity = physicalCountQuantity;
        this.bookValue = bookValue;
        this.bookDepreciation = bookDepreciation;
        this.netBookValue = netBookValue;
        this.gainingMethod = gainingMethod;
        this.specificationsAndModels = specificationsAndModels;
        this.unitOfMeasurement = unitOfMeasurement;
        this.dateOfAcquisition = dateOfAcquisition;
        this.dateOfFinancialEntry = dateOfFinancialEntry;
        this.typeOfValue = typeOfValue;
        this.storagePlace = storagePlace;
        this.userDepartment = userDepartment;
        this.user = user;
        this.originalAssetNumber = originalAssetNumber;
        this.remark = remark;
        this.inventoryData = inventoryData;
        this.ownershipDataSheet = ownershipDataSheet;
    }

    public School(String assetNumber, String assetName, String assetClassification,
                  String nationalStandardClassification, String actualNumberOf,
                  String actualValue, String actualAccumulatedDepreciation, String inventoryResults,
                  String useStatus, String serialNumber, String physicalCountQuantity,
                  String bookValue, String bookDepreciation, String netBookValue,
                  String gainingMethod, String specificationsAndModels, String unitOfMeasurement,
                  String dateOfAcquisition, String dateOfFinancialEntry, String typeOfValue,
                  String storagePlace, String userDepartment, String user,
                  String originalAssetNumber, String remark, String ownershipDataSheet) {
        this.assetNumber = assetNumber;
        this.assetName = assetName;
        this.assetClassification = assetClassification;
        this.nationalStandardClassification = nationalStandardClassification;
        this.actualNumberOf = actualNumberOf;
        this.actualValue = actualValue;
        this.actualAccumulatedDepreciation = actualAccumulatedDepreciation;
        this.inventoryResults = inventoryResults;
        this.useStatus = useStatus;
        this.serialNumber = serialNumber;
        this.physicalCountQuantity = physicalCountQuantity;
        this.bookValue = bookValue;
        this.bookDepreciation = bookDepreciation;
        this.netBookValue = netBookValue;
        this.gainingMethod = gainingMethod;
        this.specificationsAndModels = specificationsAndModels;
        this.unitOfMeasurement = unitOfMeasurement;
        this.dateOfAcquisition = dateOfAcquisition;
        this.dateOfFinancialEntry = dateOfFinancialEntry;
        this.typeOfValue = typeOfValue;
        this.storagePlace = storagePlace;
        this.userDepartment = userDepartment;
        this.user = user;
        this.originalAssetNumber = originalAssetNumber;
        this.remark = remark;
        this.ownershipDataSheet = ownershipDataSheet;
    }

    public String getOwnershipDataSheet() {
        return ownershipDataSheet;
    }

    public void setOwnershipDataSheet(String ownershipDataSheet) {
        this.ownershipDataSheet = ownershipDataSheet;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetClassification() {
        return assetClassification;
    }

    public void setAssetClassification(String assetClassification) {
        this.assetClassification = assetClassification;
    }

    public String getNationalStandardClassification() {
        return nationalStandardClassification;
    }

    public void setNationalStandardClassification(String nationalStandardClassification) {
        this.nationalStandardClassification = nationalStandardClassification;
    }

    public String getActualNumberOf() {
        return actualNumberOf;
    }

    public void setActualNumberOf(String actualNumberOf) {
        this.actualNumberOf = actualNumberOf;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getActualAccumulatedDepreciation() {
        return actualAccumulatedDepreciation;
    }

    public void setActualAccumulatedDepreciation(String actualAccumulatedDepreciation) {
        this.actualAccumulatedDepreciation = actualAccumulatedDepreciation;
    }

    public String getInventoryResults() {
        return inventoryResults;
    }

    public void setInventoryResults(String inventoryResults) {
        this.inventoryResults = inventoryResults;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPhysicalCountQuantity() {
        return physicalCountQuantity;
    }

    public void setPhysicalCountQuantity(String physicalCountQuantity) {
        this.physicalCountQuantity = physicalCountQuantity;
    }

    public String getBookValue() {
        return bookValue;
    }

    public void setBookValue(String bookValue) {
        this.bookValue = bookValue;
    }

    public String getBookDepreciation() {
        return bookDepreciation;
    }

    public void setBookDepreciation(String bookDepreciation) {
        this.bookDepreciation = bookDepreciation;
    }

    public String getNetBookValue() {
        return netBookValue;
    }

    public void setNetBookValue(String netBookValue) {
        this.netBookValue = netBookValue;
    }

    public String getGainingMethod() {
        return gainingMethod;
    }

    public void setGainingMethod(String gainingMethod) {
        this.gainingMethod = gainingMethod;
    }

    public String getSpecificationsAndModels() {
        return specificationsAndModels;
    }

    public void setSpecificationsAndModels(String specificationsAndModels) {
        this.specificationsAndModels = specificationsAndModels;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getDateOfAcquisition() {
        return dateOfAcquisition;
    }

    public void setDateOfAcquisition(String dateOfAcquisition) {
        this.dateOfAcquisition = dateOfAcquisition;
    }

    public String getDateOfFinancialEntry() {
        return dateOfFinancialEntry;
    }

    public void setDateOfFinancialEntry(String dateOfFinancialEntry) {
        this.dateOfFinancialEntry = dateOfFinancialEntry;
    }

    public String getTypeOfValue() {
        return typeOfValue;
    }

    public void setTypeOfValue(String typeOfValue) {
        this.typeOfValue = typeOfValue;
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(String storagePlace) {
        this.storagePlace = storagePlace;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOriginalAssetNumber() {
        return originalAssetNumber;
    }

    public void setOriginalAssetNumber(String originalAssetNumber) {
        this.originalAssetNumber = originalAssetNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInventoryData() {
        return inventoryData;
    }

    public void setInventoryData(String inventoryData) {
        this.inventoryData = inventoryData;
    }
}
