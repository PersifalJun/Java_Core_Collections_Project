package collections.model;

import lombok.*;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact{
    private String name;
    private String phone;
    private String email;
    private String group;

    @Override
    public boolean equals(Object obj){
        if (!isNull(obj) && this.equals(obj)) return true;
        if (isNull(obj) || this.getClass() != obj.getClass()) return false;
        Contact contactObj = (Contact) obj;
        if (!(this.phone.equals(contactObj.phone))) return false;
        return (nonNull(this.name) ? this.name.equals(contactObj.name) : isNull(contactObj.name));
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, phone);
    }
    @Override
    public String toString(){
        return "Contact: " + "\n name: " + name + "\n phone: " + phone + "\n email: " + email + "\n group: " + group;
    }


}