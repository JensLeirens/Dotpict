package mafken.dotpict;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jensleirens on 17/08/2017.
 */

@Entity
public class Pixel {
    @Id
    private Long id;

    private Long drawingId;
    private int color;

    public Pixel(int color) {
        this.color = color;
    }

    @Generated(hash = 1012342511)
    public Pixel(Long id, Long drawingId, int color) {
        this.id = id;
        this.drawingId = drawingId;
        this.color = color;
    }

    @Generated(hash = 1218219172)
    public Pixel() {
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrawingId() {
        return this.drawingId;
    }

    public void setDrawingId(Long drawingId) {
        this.drawingId = drawingId;
    }
}
