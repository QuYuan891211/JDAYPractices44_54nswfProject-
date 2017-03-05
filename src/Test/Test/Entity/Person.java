package Test.Test.Entity;

import java.io.Serializable;

/**
 * Created by qy on 2017/2/23.
 */
public class Person implements Serializable {
    public Person() {
    }

    public Person( String name) {

        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String id;
    public String name;

}
