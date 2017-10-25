package shad.cookbook.data.model.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(nameInDb = "ingredient")
public class IngredientEntity {
    @Id(autoincrement = true)
    private long id;

//    @Property(nameInDb = "recipe_id")
    private long recipeId;

//    @Property(nameInDb = "food_id")
    private long foodId;

    @Property
    private String name;

    @Property
    private int amount;

    private long unitId;

    @ToOne(joinProperty = "unitId")
    private UnitEntity unit;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 817604089)
    private transient IngredientEntityDao myDao;

    @Generated(hash = 1167209440)
    public IngredientEntity(long id, long recipeId, long foodId, String name,
            int amount, long unitId) {
        this.id = id;
        this.recipeId = recipeId;
        this.foodId = foodId;
        this.name = name;
        this.amount = amount;
        this.unitId = unitId;
    }

    @Generated(hash = 1567344080)
    public IngredientEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecipeId() {
        return this.recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getFoodId() {
        return this.foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getUnitId() {
        return this.unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    @Generated(hash = 1783304678)
    private transient Long unit__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 616022433)
    public UnitEntity getUnit() {
        long __key = this.unitId;
        if (unit__resolvedKey == null || !unit__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UnitEntityDao targetDao = daoSession.getUnitEntityDao();
            UnitEntity unitNew = targetDao.load(__key);
            synchronized (this) {
                unit = unitNew;
                unit__resolvedKey = __key;
            }
        }
        return unit;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1194476912)
    public void setUnit(@NotNull UnitEntity unit) {
        if (unit == null) {
            throw new DaoException(
                    "To-one property 'unitId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.unit = unit;
            unitId = unit.getId();
            unit__resolvedKey = unitId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 573026188)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getIngredientEntityDao() : null;
    }
}
