
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

@XmlRootElement(name = "marcarConsultas_Server", namespace = "http://default_package/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "marcarConsultas_Server", namespace = "http://default_package/")

public class MarcarConsultas_Server {

    @XmlElement(name = "arg0")
    private java.lang.String arg0;

    public java.lang.String getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.String newArg0)  {
        this.arg0 = newArg0;
    }

}

