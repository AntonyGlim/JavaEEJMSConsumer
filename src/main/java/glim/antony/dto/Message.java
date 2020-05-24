package glim.antony.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

@XmlType(name = "message")
@XmlRootElement
public class Message implements Serializable, Comparable<Message> {
    private static final long serialVersionUID = -90027356L;

    private Long inputNumber;
    private String inputText;
    private Date inputDate;

    public Message() {
    }

    public Message(Long inputNumber, String inputText, Date inputDate) {
        this.inputNumber = inputNumber;
        this.inputText = inputText;
        this.inputDate = inputDate;
    }

    public int compareTo(Message message) {
        if (this.inputDate == null && message.inputDate == null) return 0;
        if (this.inputDate == null) return -1;
        if (message.inputDate == null) return 1;
        return this.inputDate.compareTo(message.getInputDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        if (inputNumber != null ? !inputNumber.equals(message.inputNumber) : message.inputNumber != null) return false;
        if (inputText != null ? !inputText.equals(message.inputText) : message.inputText != null) return false;
        return inputDate != null ? inputDate.equals(message.inputDate) : message.inputDate == null;
    }

    @Override
    public int hashCode() {
        int result = inputText.hashCode();
        result = 31 * result + inputNumber.hashCode();
        result = 31 * result + inputDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "inputNumber=" + inputNumber +
                ", inputText='" + inputText + '\'' +
                ", inputDate=" + inputDate +
                '}';
    }

    public Long getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(Long inputNumber) {
        this.inputNumber = inputNumber;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}