package mafken.dotpict;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by jensleirens on 17/08/2017.
 */
@Entity
public class Drawing {
    @Id
    private Long id;

    @ToMany(referencedJoinProperty = "drawingId")
    private List<Pixel> colors = new ArrayList<>();

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 348974505)
    private transient DrawingDao myDao;

    public Drawing(ArrayList<Pixel> colors) {
        this.colors = colors;
    }

    @Generated(hash = 2071169441)
    public Drawing(Long id) {
        this.id = id;
    }

    @Generated(hash = 650462471)
    public Drawing() {
    }

    public List<Pixel> getColor() {
        return colors;
    }

    public void setColor(List<Pixel> color) {
        this.colors = color;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2013272490)
    public List<Pixel> getColors() {
        if (colors == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PixelDao targetDao = daoSession.getPixelDao();
            List<Pixel> colorsNew = targetDao._queryDrawing_Colors(id);
            synchronized (this) {
                if (colors == null) {
                    colors = colorsNew;
                }
            }
        }
        return colors;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1454211756)
    public synchronized void resetColors() {
        colors = null;
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
    @Generated(hash = 2127937703)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDrawingDao() : null;
    }
}