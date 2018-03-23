package com.uniovi.entitites;



import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notificatios")
public class Notification {

    @Id
    private ObjectId id;
    
    private Operator operator;

    private Incident incident;

    public Notification() {
    }

    public Notification(ObjectId id, Incident incident, Operator operator) {
	this(incident, operator);
	this.id = id;
    }

    public Notification(Incident incident, Operator operator) {
	super();
	this.incident = incident;
	this.operator = operator;
    }

    public Operator getOperator() {
	return operator;
    }

    public void setOperator(Operator operator) {
	this.operator = operator;
    }

    public Incident getIncident() {
	return incident;
    }

    public void setIncident(Incident incident) {
	this.incident = incident;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((operator == null) ? 0 : operator.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Notification other = (Notification) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (operator == null) {
	    if (other.operator != null)
		return false;
	} else if (!operator.equals(other.operator))
	    return false;
	return true;
    }

}
