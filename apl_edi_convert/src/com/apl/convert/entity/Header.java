//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.11.11 ʱ�� 11:54:26 AM CST 
//


package com.apl.convert.entity;

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
 *         &lt;element name="Action">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ActionSource">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ActionReason">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BookingType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BookingDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="BookingTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="PONo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ShipperReferenceNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SDDFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SDOFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CargoReceiptMode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CargoDeliveryMode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FreeInOut">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PaymentTermOCF" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BookingOffice" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLReleaseOffice" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ContractType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="QuotationNo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ChannelOfBooking" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Origin" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Destination" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LoadPort" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DischargePort" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LoadVessel" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LoadVoyage" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DepartAfterDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DepartAfterTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;element name="ArriveByDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ArriveByTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;element name="IMDGAmmendmentNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PCFN" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="VOYDOC" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ShipperDODACC" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ConsigneeDODACC" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="GovernmentMHHGType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MHHG" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="InvitationNo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FASNo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NDNo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLSubtype" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ExportReferenceInformation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLReleaseToParty" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLIssueDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="OriginOfGoods" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SeaAirFlag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ValidationCode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ValidationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CollectInvoiceReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrepaidInvoiceReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CHB" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="36"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FMCFlag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLCreationMode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BLDatedAt" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LCNumber" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CompanyCode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PrintCheckDigit" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
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
    "action",
    "actionSource",
    "actionReason",
    "blNo",
    "bookingType",
    "bookingDate",
    "bookingTime",
    "poNo",
    "shipperReferenceNo",
    "sddFlag",
    "sdoFlag",
    "cargoReceiptMode",
    "cargoDeliveryMode",
    "freeInOut",
    "paymentTermOCF",
    "bookingOffice",
    "blReleaseOffice",
    "contractType",
    "quotationNo",
    "channelOfBooking",
    "origin",
    "destination",
    "loadPort",
    "dischargePort",
    "loadVessel",
    "loadVoyage",
    "departAfterDate",
    "departAfterTime",
    "arriveByDate",
    "arriveByTime",
    "imdgAmmendmentNo",
    "pcfn",
    "voydoc",
    "shipperDODACC",
    "consigneeDODACC",
    "governmentMHHGType",
    "mhhg",
    "invitationNo",
    "fasNo",
    "ndNo",
    "blType",
    "blSubtype",
    "exportReferenceInformation",
    "blReleaseToParty",
    "blIssueDate",
    "originOfGoods",
    "seaAirFlag",
    "validationCode",
    "validationDate",
    "collectInvoiceReference",
    "prepaidInvoiceReference",
    "chb",
    "fmcFlag",
    "blCreationMode",
    "blDatedAt",
    "lcNumber",
    "companyCode",
    "printCheckDigit"
})
@XmlRootElement(name = "Header")
public class Header {

    @XmlElement(name = "Action", required = true)
    protected String action;
    @XmlElement(name = "ActionSource", required = true)
    protected String actionSource;
    @XmlElement(name = "ActionReason", required = true)
    protected String actionReason;
    @XmlElement(name = "BLNo", required = true)
    protected String blNo;
    @XmlElement(name = "BookingType", required = true)
    protected String bookingType;
    @XmlElement(name = "BookingDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar bookingDate;
    @XmlElement(name = "BookingTime", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar bookingTime;
    @XmlElement(name = "PONo", required = true)
    protected String poNo;
    @XmlElement(name = "ShipperReferenceNo", required = true)
    protected String shipperReferenceNo;
    @XmlElement(name = "SDDFlag")
    protected boolean sddFlag;
    @XmlElement(name = "SDOFlag")
    protected boolean sdoFlag;
    @XmlElement(name = "CargoReceiptMode", required = true)
    protected String cargoReceiptMode;
    @XmlElement(name = "CargoDeliveryMode", required = true)
    protected String cargoDeliveryMode;
    @XmlElement(name = "FreeInOut", required = true)
    protected String freeInOut;
    @XmlElement(name = "PaymentTermOCF")
    protected String paymentTermOCF;
    @XmlElement(name = "BookingOffice")
    protected String bookingOffice;
    @XmlElement(name = "BLReleaseOffice")
    protected String blReleaseOffice;
    @XmlElement(name = "ContractType")
    protected String contractType;
    @XmlElement(name = "QuotationNo")
    protected String quotationNo;
    @XmlElement(name = "ChannelOfBooking")
    protected String channelOfBooking;
    @XmlElement(name = "Origin")
    protected String origin;
    @XmlElement(name = "Destination")
    protected String destination;
    @XmlElement(name = "LoadPort")
    protected String loadPort;
    @XmlElement(name = "DischargePort")
    protected String dischargePort;
    @XmlElement(name = "LoadVessel")
    protected String loadVessel;
    @XmlElement(name = "LoadVoyage")
    protected String loadVoyage;
    @XmlElement(name = "DepartAfterDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar departAfterDate;
    @XmlElement(name = "DepartAfterTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar departAfterTime;
    @XmlElement(name = "ArriveByDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar arriveByDate;
    @XmlElement(name = "ArriveByTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar arriveByTime;
    @XmlElement(name = "IMDGAmmendmentNo")
    protected String imdgAmmendmentNo;
    @XmlElement(name = "PCFN")
    protected String pcfn;
    @XmlElement(name = "VOYDOC")
    protected String voydoc;
    @XmlElement(name = "ShipperDODACC")
    protected String shipperDODACC;
    @XmlElement(name = "ConsigneeDODACC")
    protected String consigneeDODACC;
    @XmlElement(name = "GovernmentMHHGType")
    protected String governmentMHHGType;
    @XmlElement(name = "MHHG")
    protected String mhhg;
    @XmlElement(name = "InvitationNo")
    protected String invitationNo;
    @XmlElement(name = "FASNo")
    protected String fasNo;
    @XmlElement(name = "NDNo")
    protected String ndNo;
    @XmlElement(name = "BLType")
    protected String blType;
    @XmlElement(name = "BLSubtype")
    protected String blSubtype;
    @XmlElement(name = "ExportReferenceInformation")
    protected String exportReferenceInformation;
    @XmlElement(name = "BLReleaseToParty")
    protected String blReleaseToParty;
    @XmlElement(name = "BLIssueDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar blIssueDate;
    @XmlElement(name = "OriginOfGoods")
    protected String originOfGoods;
    @XmlElement(name = "SeaAirFlag")
    protected String seaAirFlag;
    @XmlElement(name = "ValidationCode")
    protected String validationCode;
    @XmlElement(name = "ValidationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validationDate;
    @XmlElement(name = "CollectInvoiceReference")
    protected String collectInvoiceReference;
    @XmlElement(name = "PrepaidInvoiceReference")
    protected String prepaidInvoiceReference;
    @XmlElement(name = "CHB")
    protected String chb;
    @XmlElement(name = "FMCFlag")
    protected String fmcFlag;
    @XmlElement(name = "BLCreationMode")
    protected String blCreationMode;
    @XmlElement(name = "BLDatedAt")
    protected String blDatedAt;
    @XmlElement(name = "LCNumber")
    protected String lcNumber;
    @XmlElement(name = "CompanyCode")
    protected String companyCode;
    @XmlElement(name = "PrintCheckDigit")
    protected String printCheckDigit;

    /**
     * ��ȡaction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * ����action���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * ��ȡactionSource���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionSource() {
        return actionSource;
    }

    /**
     * ����actionSource���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionSource(String value) {
        this.actionSource = value;
    }

    /**
     * ��ȡactionReason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionReason() {
        return actionReason;
    }

    /**
     * ����actionReason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionReason(String value) {
        this.actionReason = value;
    }

    /**
     * ��ȡblNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLNo() {
        return blNo;
    }

    /**
     * ����blNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLNo(String value) {
        this.blNo = value;
    }

    /**
     * ��ȡbookingType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingType() {
        return bookingType;
    }

    /**
     * ����bookingType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingType(String value) {
        this.bookingType = value;
    }

    /**
     * ��ȡbookingDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBookingDate() {
        return bookingDate;
    }

    /**
     * ����bookingDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBookingDate(XMLGregorianCalendar value) {
        this.bookingDate = value;
    }

    /**
     * ��ȡbookingTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBookingTime() {
        return bookingTime;
    }

    /**
     * ����bookingTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBookingTime(XMLGregorianCalendar value) {
        this.bookingTime = value;
    }

    /**
     * ��ȡpoNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPONo() {
        return poNo;
    }

    /**
     * ����poNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPONo(String value) {
        this.poNo = value;
    }

    /**
     * ��ȡshipperReferenceNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperReferenceNo() {
        return shipperReferenceNo;
    }

    /**
     * ����shipperReferenceNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperReferenceNo(String value) {
        this.shipperReferenceNo = value;
    }

    /**
     * ��ȡsddFlag���Ե�ֵ��
     * 
     */
    public boolean isSDDFlag() {
        return sddFlag;
    }

    /**
     * ����sddFlag���Ե�ֵ��
     * 
     */
    public void setSDDFlag(boolean value) {
        this.sddFlag = value;
    }

    /**
     * ��ȡsdoFlag���Ե�ֵ��
     * 
     */
    public boolean isSDOFlag() {
        return sdoFlag;
    }

    /**
     * ����sdoFlag���Ե�ֵ��
     * 
     */
    public void setSDOFlag(boolean value) {
        this.sdoFlag = value;
    }

    /**
     * ��ȡcargoReceiptMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargoReceiptMode() {
        return cargoReceiptMode;
    }

    /**
     * ����cargoReceiptMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargoReceiptMode(String value) {
        this.cargoReceiptMode = value;
    }

    /**
     * ��ȡcargoDeliveryMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargoDeliveryMode() {
        return cargoDeliveryMode;
    }

    /**
     * ����cargoDeliveryMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargoDeliveryMode(String value) {
        this.cargoDeliveryMode = value;
    }

    /**
     * ��ȡfreeInOut���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreeInOut() {
        return freeInOut;
    }

    /**
     * ����freeInOut���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreeInOut(String value) {
        this.freeInOut = value;
    }

    /**
     * ��ȡpaymentTermOCF���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTermOCF() {
        return paymentTermOCF;
    }

    /**
     * ����paymentTermOCF���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTermOCF(String value) {
        this.paymentTermOCF = value;
    }

    /**
     * ��ȡbookingOffice���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingOffice() {
        return bookingOffice;
    }

    /**
     * ����bookingOffice���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingOffice(String value) {
        this.bookingOffice = value;
    }

    /**
     * ��ȡblReleaseOffice���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLReleaseOffice() {
        return blReleaseOffice;
    }

    /**
     * ����blReleaseOffice���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLReleaseOffice(String value) {
        this.blReleaseOffice = value;
    }

    /**
     * ��ȡcontractType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * ����contractType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractType(String value) {
        this.contractType = value;
    }

    /**
     * ��ȡquotationNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotationNo() {
        return quotationNo;
    }

    /**
     * ����quotationNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotationNo(String value) {
        this.quotationNo = value;
    }

    /**
     * ��ȡchannelOfBooking���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelOfBooking() {
        return channelOfBooking;
    }

    /**
     * ����channelOfBooking���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelOfBooking(String value) {
        this.channelOfBooking = value;
    }

    /**
     * ��ȡorigin���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * ����origin���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * ��ȡdestination���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * ����destination���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * ��ȡloadPort���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadPort() {
        return loadPort;
    }

    /**
     * ����loadPort���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadPort(String value) {
        this.loadPort = value;
    }

    /**
     * ��ȡdischargePort���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDischargePort() {
        return dischargePort;
    }

    /**
     * ����dischargePort���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDischargePort(String value) {
        this.dischargePort = value;
    }

    /**
     * ��ȡloadVessel���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadVessel() {
        return loadVessel;
    }

    /**
     * ����loadVessel���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadVessel(String value) {
        this.loadVessel = value;
    }

    /**
     * ��ȡloadVoyage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadVoyage() {
        return loadVoyage;
    }

    /**
     * ����loadVoyage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadVoyage(String value) {
        this.loadVoyage = value;
    }

    /**
     * ��ȡdepartAfterDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartAfterDate() {
        return departAfterDate;
    }

    /**
     * ����departAfterDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartAfterDate(XMLGregorianCalendar value) {
        this.departAfterDate = value;
    }

    /**
     * ��ȡdepartAfterTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartAfterTime() {
        return departAfterTime;
    }

    /**
     * ����departAfterTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartAfterTime(XMLGregorianCalendar value) {
        this.departAfterTime = value;
    }

    /**
     * ��ȡarriveByDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArriveByDate() {
        return arriveByDate;
    }

    /**
     * ����arriveByDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArriveByDate(XMLGregorianCalendar value) {
        this.arriveByDate = value;
    }

    /**
     * ��ȡarriveByTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArriveByTime() {
        return arriveByTime;
    }

    /**
     * ����arriveByTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArriveByTime(XMLGregorianCalendar value) {
        this.arriveByTime = value;
    }

    /**
     * ��ȡimdgAmmendmentNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMDGAmmendmentNo() {
        return imdgAmmendmentNo;
    }

    /**
     * ����imdgAmmendmentNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMDGAmmendmentNo(String value) {
        this.imdgAmmendmentNo = value;
    }

    /**
     * ��ȡpcfn���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCFN() {
        return pcfn;
    }

    /**
     * ����pcfn���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCFN(String value) {
        this.pcfn = value;
    }

    /**
     * ��ȡvoydoc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOYDOC() {
        return voydoc;
    }

    /**
     * ����voydoc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOYDOC(String value) {
        this.voydoc = value;
    }

    /**
     * ��ȡshipperDODACC���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperDODACC() {
        return shipperDODACC;
    }

    /**
     * ����shipperDODACC���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperDODACC(String value) {
        this.shipperDODACC = value;
    }

    /**
     * ��ȡconsigneeDODACC���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsigneeDODACC() {
        return consigneeDODACC;
    }

    /**
     * ����consigneeDODACC���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsigneeDODACC(String value) {
        this.consigneeDODACC = value;
    }

    /**
     * ��ȡgovernmentMHHGType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovernmentMHHGType() {
        return governmentMHHGType;
    }

    /**
     * ����governmentMHHGType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovernmentMHHGType(String value) {
        this.governmentMHHGType = value;
    }

    /**
     * ��ȡmhhg���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMHHG() {
        return mhhg;
    }

    /**
     * ����mhhg���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMHHG(String value) {
        this.mhhg = value;
    }

    /**
     * ��ȡinvitationNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvitationNo() {
        return invitationNo;
    }

    /**
     * ����invitationNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvitationNo(String value) {
        this.invitationNo = value;
    }

    /**
     * ��ȡfasNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFASNo() {
        return fasNo;
    }

    /**
     * ����fasNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFASNo(String value) {
        this.fasNo = value;
    }

    /**
     * ��ȡndNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNDNo() {
        return ndNo;
    }

    /**
     * ����ndNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNDNo(String value) {
        this.ndNo = value;
    }

    /**
     * ��ȡblType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLType() {
        return blType;
    }

    /**
     * ����blType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLType(String value) {
        this.blType = value;
    }

    /**
     * ��ȡblSubtype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLSubtype() {
        return blSubtype;
    }

    /**
     * ����blSubtype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLSubtype(String value) {
        this.blSubtype = value;
    }

    /**
     * ��ȡexportReferenceInformation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportReferenceInformation() {
        return exportReferenceInformation;
    }

    /**
     * ����exportReferenceInformation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportReferenceInformation(String value) {
        this.exportReferenceInformation = value;
    }

    /**
     * ��ȡblReleaseToParty���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLReleaseToParty() {
        return blReleaseToParty;
    }

    /**
     * ����blReleaseToParty���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLReleaseToParty(String value) {
        this.blReleaseToParty = value;
    }

    /**
     * ��ȡblIssueDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBLIssueDate() {
        return blIssueDate;
    }

    /**
     * ����blIssueDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBLIssueDate(XMLGregorianCalendar value) {
        this.blIssueDate = value;
    }

    /**
     * ��ȡoriginOfGoods���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginOfGoods() {
        return originOfGoods;
    }

    /**
     * ����originOfGoods���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginOfGoods(String value) {
        this.originOfGoods = value;
    }

    /**
     * ��ȡseaAirFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeaAirFlag() {
        return seaAirFlag;
    }

    /**
     * ����seaAirFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeaAirFlag(String value) {
        this.seaAirFlag = value;
    }

    /**
     * ��ȡvalidationCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationCode() {
        return validationCode;
    }

    /**
     * ����validationCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationCode(String value) {
        this.validationCode = value;
    }

    /**
     * ��ȡvalidationDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidationDate() {
        return validationDate;
    }

    /**
     * ����validationDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidationDate(XMLGregorianCalendar value) {
        this.validationDate = value;
    }

    /**
     * ��ȡcollectInvoiceReference���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectInvoiceReference() {
        return collectInvoiceReference;
    }

    /**
     * ����collectInvoiceReference���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectInvoiceReference(String value) {
        this.collectInvoiceReference = value;
    }

    /**
     * ��ȡprepaidInvoiceReference���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidInvoiceReference() {
        return prepaidInvoiceReference;
    }

    /**
     * ����prepaidInvoiceReference���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidInvoiceReference(String value) {
        this.prepaidInvoiceReference = value;
    }

    /**
     * ��ȡchb���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHB() {
        return chb;
    }

    /**
     * ����chb���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHB(String value) {
        this.chb = value;
    }

    /**
     * ��ȡfmcFlag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFMCFlag() {
        return fmcFlag;
    }

    /**
     * ����fmcFlag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFMCFlag(String value) {
        this.fmcFlag = value;
    }

    /**
     * ��ȡblCreationMode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLCreationMode() {
        return blCreationMode;
    }

    /**
     * ����blCreationMode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLCreationMode(String value) {
        this.blCreationMode = value;
    }

    /**
     * ��ȡblDatedAt���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBLDatedAt() {
        return blDatedAt;
    }

    /**
     * ����blDatedAt���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBLDatedAt(String value) {
        this.blDatedAt = value;
    }

    /**
     * ��ȡlcNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLCNumber() {
        return lcNumber;
    }

    /**
     * ����lcNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLCNumber(String value) {
        this.lcNumber = value;
    }

    /**
     * ��ȡcompanyCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * ����companyCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyCode(String value) {
        this.companyCode = value;
    }

    /**
     * ��ȡprintCheckDigit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintCheckDigit() {
        return printCheckDigit;
    }

    /**
     * ����printCheckDigit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintCheckDigit(String value) {
        this.printCheckDigit = value;
    }

}
