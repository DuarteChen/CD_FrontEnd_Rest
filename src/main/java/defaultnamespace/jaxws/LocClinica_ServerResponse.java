
package defaultnamespace.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.6.4
 * Wed Dec 11 17:40:42 WET 2024
 * Generated source version: 3.6.4
 */

@XmlRootElement(name = "locClinica_ServerResponse", namespace = "http://default_package/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locClinica_ServerResponse", namespace = "http://default_package/")

public class LocClinica_ServerResponse {

    @XmlElement(name = "return")
    private java.lang.String _return;

    public java.lang.String getReturn() {
        return this._return;
    }

    public void setReturn(java.lang.String new_return)  {
        this._return = new_return;
    }

}

