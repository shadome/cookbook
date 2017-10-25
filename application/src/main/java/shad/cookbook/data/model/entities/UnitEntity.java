package shad.cookbook.data.model.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "unit")
public class UnitEntity {
    @Id(autoincrement = true)
    private long id;

    /**
     * Considering 1960's 11th General Conference on Weighs and Measures.
     * http://www.les-abreviations.com/symboles-international.html
     */
    @Property
    private String abbrName;

    @Property
    private String fullName;

    @Generated(hash = 469745943)
    public UnitEntity(long id, String abbrName, String fullName) {
        this.id = id;
        this.abbrName = abbrName;
        this.fullName = fullName;
    }

    @Generated(hash = 954779494)
    public UnitEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAbbrName() {
        return this.abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
