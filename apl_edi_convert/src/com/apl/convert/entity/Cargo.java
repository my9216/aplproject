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
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Measurement" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MeasurementType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="MeasurementValue" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="6"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="MeasurementUOM" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="3"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="InnerPkgType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="17"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OuterPkgType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="17"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Marks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripton" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "measurement",
    "innerPkgType",
    "outerPkgType",
    "marks",
    "descripton"
})
@XmlRootElement(name = "Cargo")
public class Cargo {

    @XmlElement(name = "Measurement")
    protected List<Cargo.Measurement> measurement;
    @XmlElement(name = "InnerPkgType")
    protected String innerPkgType;
    @XmlElement(name = "OuterPkgType")
    protected String outerPkgType;
    @XmlElement(name = "Marks")
    protected String marks;
    @XmlElement(name = "Descripton")
    protected String descripton;

    /**
     * Gets the value of the measurement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measurement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasurement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cargo.Measurement }
     * 
     * 
     */
    public List<Cargo.Measurement> getMeasurement() {
        if (measurement == null) {
            measurement = new ArrayList<Cargo.Measurement>();
        }
        return this.measurement;
    }

    /**
     * ��ȡinnerPkgType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInnerPkgType() {
        return innerPkgType;
    }

    /**
     * ����innerPkgType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInnerPkgType(String value) {
        this.innerPkgType = value;
    }

    /**
     * ��ȡouterPkgType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOuterPkgType() {
        return outerPkgType;
    }

    /**
     * ����outerPkgType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOuterPkgType(String value) {
        this.outerPkgType = value;
    }

    /**
     * ��ȡmarks���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarks() {
        return marks;
    }

    /**
     * ����marks���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarks(String value) {
        this.marks = value;
    }

    /**
     * ��ȡdescripton���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripton() {
        return descripton;
    }

    /**
     * ����descripton���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripton(String value) {
        this.descripton = value;
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
     *         &lt;element name="MeasurementType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="MeasurementValue" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="6"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="MeasurementUOM" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="3"/>
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
        "measurementType",
        "measurementValue",
        "measurementUOM"
    })
    public static class Measurement {

        @XmlElement(name = "MeasurementType")
        protected String measurementType;
        @XmlElement(name = "MeasurementValue")
        protected String measurementValue;
        @XmlElement(name = "MeasurementUOM")
        protected String measurementUOM;

        /**
         * ��ȡmeasurementType���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMeasurementType() {
            return measurementType;
        }

        /**
         * ����measurementType���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMeasurementType(String value) {
            this.measurementType = value;
        }

        /**
         * ��ȡmeasurementValue���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMeasurementValue() {
            return measurementValue;
        }

        /**
         * ����measurementValue���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMeasurementValue(String value) {
            this.measurementValue = value;
        }

        /**
         * ��ȡmeasurementUOM���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMeasurementUOM() {
            return measurementUOM;
        }

        /**
         * ����measurementUOM���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMeasurementUOM(String value) {
            this.measurementUOM = value;
        }

    }

}
