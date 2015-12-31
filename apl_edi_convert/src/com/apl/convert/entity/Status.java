//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.11.11 ʱ�� 11:54:26 AM CST 
//


package com.apl.convert.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BookingStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CarrierHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CustomsHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SplitInidicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ConsolidateIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RollCounter" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BLStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PnRCheckStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProhibitionStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="RestrictionStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="RegulatoryCheckStatusofBL" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EquipmentReconcilationStatusofBL" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PrintStatusofBL" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLReleaseStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PrepaidPaymentStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CargoReleaseStatusofBL" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CollectPaymentStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLSurrenderStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ReasonCode" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StatusRef" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="10"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ReasonCode" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="8"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ReasonCodeRemarks" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BLSurrender" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SurrenderDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="SurrenderOfficeLocation" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="NoOfBLSurrendered" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bookingStatus",
    "carrierHold",
    "customsHold",
    "splitInidicator",
    "consolidateIndicator",
    "rollCounter",
    "blStatus",
    "pnRCheckStatus",
    "prohibitionStatus",
    "restrictionStatus",
    "regulatoryCheckStatusofBL",
    "equipmentReconcilationStatusofBL",
    "printStatusofBL",
    "blReleaseStatus",
    "prepaidPaymentStatus",
    "cargoReleaseStatusofBL",
    "collectPaymentStatus",
    "blSurrenderStatus",
    "reasonCode",
    "blSurrender"
})
@XmlRootElement(name = "Status")
public class Status {

    @XmlElement(name = "BookingStatus")
    protected String bookingStatus;
    @XmlElement(name = "CarrierHold")
    protected Boolean carrierHold;
    @XmlElement(name = "CustomsHold")
    protected Boolean customsHold;
    @XmlElement(name = "SplitInidicator")
    protected Boolean splitInidicator;
    @XmlElement(name = "ConsolidateIndicator")
    protected Boolean consolidateIndicator;
    @XmlElement(name = "RollCounter")
    protected Integer rollCounter;
    @XmlElement(name = "BLStatus")
    protected String blStatus;
    @XmlElement(name = "PnRCheckStatus")
    protected String pnRCheckStatus;
    @XmlElement(name = "ProhibitionStatus")
    protected String prohibitionStatus;
    @XmlElement(name = "RestrictionStatus")
    protected String restrictionStatus;
    @XmlElement(name = "RegulatoryCheckStatusofBL")
    protected String regulatoryCheckStatusofBL;
    @XmlElement(name = "EquipmentReconcilationStatusofBL")
    protected String equipmentReconcilationStatusofBL;
    @XmlElement(name = "PrintStatusofBL")
    protected String printStatusofBL;
    @XmlElement(name = "BLReleaseStatus")
    protected String blReleaseStatus;
    @XmlElement(name = "PrepaidPaymentStatus")
    protected String prepaidPaymentStatus;
    @XmlElement(name = "CargoReleaseStatusofBL")
    protected String cargoReleaseStatusofBL;
    @XmlElement(name = "CollectPaymentStatus")
    protected String collectPaymentStatus;
    @XmlElement(name = "BLSurrenderStatus")
    protected String blSurrenderStatus;
    @XmlElement(name = "ReasonCode")
    protected List<Status.ReasonCode> reasonCode;
    @XmlElement(name = "BLSurrender")
    protected Status.BLSurrender blSurrender;

    /**
     * ��ȡbookingStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingStatus() {
        return bookingStatus;
    }

    /**
     * ����bookingStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingStatus(String value) {
        this.bookingStatus = value;
    }

    /**
     * ��ȡcarrierHold���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCarrierHold() {
        return carrierHold;
    }

    /**
     * ����carrierHold���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCarrierHold(Boolean value) {
        this.carrierHold = value;
    }

    /**
     * ��ȡcustomsHold���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomsHold() {
        return customsHold;
    }

    /**
     * ����customsHold���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomsHold(Boolean value) {
        this.customsHold = value;
    }

    /**
     * ��ȡsplitInidicator���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSplitInidicator() {
        return splitInidicator;
    }

    /**
     * ����splitInidicator���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSplitInidicator(Boolean value) {
        this.splitInidicator = value;
    }

    /**
     * ��ȡconsolidateIndicator���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConsolidateIndicator() {
        return consolidateIndicator;
    }

    /**
     * ����consolidateIndicator���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConsolidateIndicator(Boolean value) {
        this.consolidateIndicator = value;
    }

    /**
     * ��ȡrollCounter���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRollCounter() {
        return rollCounter;
    }

    /**
     * ����rollCounter���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRollCounter(Integer value) {
        this.rollCounter = value;
    }

    /**
     * ��ȡblStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLStatus() {
        return blStatus;
    }

    /**
     * ����blStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLStatus(String value) {
        this.blStatus = value;
    }

    /**
     * ��ȡpnRCheckStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnRCheckStatus() {
        return pnRCheckStatus;
    }

    /**
     * ����pnRCheckStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnRCheckStatus(String value) {
        this.pnRCheckStatus = value;
    }

    /**
     * ��ȡprohibitionStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProhibitionStatus() {
        return prohibitionStatus;
    }

    /**
     * ����prohibitionStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProhibitionStatus(String value) {
        this.prohibitionStatus = value;
    }

    /**
     * ��ȡrestrictionStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestrictionStatus() {
        return restrictionStatus;
    }

    /**
     * ����restrictionStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestrictionStatus(String value) {
        this.restrictionStatus = value;
    }

    /**
     * ��ȡregulatoryCheckStatusofBL���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegulatoryCheckStatusofBL() {
        return regulatoryCheckStatusofBL;
    }

    /**
     * ����regulatoryCheckStatusofBL���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegulatoryCheckStatusofBL(String value) {
        this.regulatoryCheckStatusofBL = value;
    }

    /**
     * ��ȡequipmentReconcilationStatusofBL���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipmentReconcilationStatusofBL() {
        return equipmentReconcilationStatusofBL;
    }

    /**
     * ����equipmentReconcilationStatusofBL���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipmentReconcilationStatusofBL(String value) {
        this.equipmentReconcilationStatusofBL = value;
    }

    /**
     * ��ȡprintStatusofBL���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintStatusofBL() {
        return printStatusofBL;
    }

    /**
     * ����printStatusofBL���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintStatusofBL(String value) {
        this.printStatusofBL = value;
    }

    /**
     * ��ȡblReleaseStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLReleaseStatus() {
        return blReleaseStatus;
    }

    /**
     * ����blReleaseStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLReleaseStatus(String value) {
        this.blReleaseStatus = value;
    }

    /**
     * ��ȡprepaidPaymentStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidPaymentStatus() {
        return prepaidPaymentStatus;
    }

    /**
     * ����prepaidPaymentStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidPaymentStatus(String value) {
        this.prepaidPaymentStatus = value;
    }

    /**
     * ��ȡcargoReleaseStatusofBL���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargoReleaseStatusofBL() {
        return cargoReleaseStatusofBL;
    }

    /**
     * ����cargoReleaseStatusofBL���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargoReleaseStatusofBL(String value) {
        this.cargoReleaseStatusofBL = value;
    }

    /**
     * ��ȡcollectPaymentStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectPaymentStatus() {
        return collectPaymentStatus;
    }

    /**
     * ����collectPaymentStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectPaymentStatus(String value) {
        this.collectPaymentStatus = value;
    }

    /**
     * ��ȡblSurrenderStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLSurrenderStatus() {
        return blSurrenderStatus;
    }

    /**
     * ����blSurrenderStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLSurrenderStatus(String value) {
        this.blSurrenderStatus = value;
    }

    /**
     * Gets the value of the reasonCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reasonCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReasonCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Status.ReasonCode }
     * 
     * 
     */
    public List<Status.ReasonCode> getReasonCode() {
        if (reasonCode == null) {
            reasonCode = new ArrayList<Status.ReasonCode>();
        }
        return this.reasonCode;
    }

    /**
     * ��ȡblSurrender���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Status.BLSurrender }
     *     
     */
    public Status.BLSurrender getBLSurrender() {
        return blSurrender;
    }

    /**
     * ����blSurrender���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Status.BLSurrender }
     *     
     */
    public void setBLSurrender(Status.BLSurrender value) {
        this.blSurrender = value;
    }


    /**
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SurrenderDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="SurrenderOfficeLocation" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="NoOfBLSurrendered" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "surrenderDate",
        "surrenderOfficeLocation",
        "noOfBLSurrendered"
    })
    public static class BLSurrender {

        @XmlElement(name = "SurrenderDate")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar surrenderDate;
        @XmlElement(name = "SurrenderOfficeLocation")
        protected String surrenderOfficeLocation;
        @XmlElement(name = "NoOfBLSurrendered")
        protected String noOfBLSurrendered;

        /**
         * ��ȡsurrenderDate���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getSurrenderDate() {
            return surrenderDate;
        }

        /**
         * ����surrenderDate���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setSurrenderDate(XMLGregorianCalendar value) {
            this.surrenderDate = value;
        }

        /**
         * ��ȡsurrenderOfficeLocation���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSurrenderOfficeLocation() {
            return surrenderOfficeLocation;
        }

        /**
         * ����surrenderOfficeLocation���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSurrenderOfficeLocation(String value) {
            this.surrenderOfficeLocation = value;
        }

        /**
         * ��ȡnoOfBLSurrendered���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNoOfBLSurrendered() {
            return noOfBLSurrendered;
        }

        /**
         * ����noOfBLSurrendered���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNoOfBLSurrendered(String value) {
            this.noOfBLSurrendered = value;
        }

    }


    /**
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="StatusRef" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="10"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ReasonCode" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="8"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ReasonCodeRemarks" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "statusRef",
        "reasonCode",
        "reasonCodeRemarks"
    })
    public static class ReasonCode {

        @XmlElement(name = "StatusRef")
        protected String statusRef;
        @XmlElement(name = "ReasonCode")
        protected String reasonCode;
        @XmlElement(name = "ReasonCodeRemarks")
        protected String reasonCodeRemarks;

        /**
         * ��ȡstatusRef���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatusRef() {
            return statusRef;
        }

        /**
         * ����statusRef���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatusRef(String value) {
            this.statusRef = value;
        }

        /**
         * ��ȡreasonCode���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReasonCode() {
            return reasonCode;
        }

        /**
         * ����reasonCode���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReasonCode(String value) {
            this.reasonCode = value;
        }

        /**
         * ��ȡreasonCodeRemarks���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReasonCodeRemarks() {
            return reasonCodeRemarks;
        }

        /**
         * ����reasonCodeRemarks���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReasonCodeRemarks(String value) {
            this.reasonCodeRemarks = value;
        }

    }

}
