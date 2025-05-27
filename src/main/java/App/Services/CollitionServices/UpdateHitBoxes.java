package App.Services.CollitionServices;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;

import static Domain.Settings.SettingsGame.TILE_SIZE;

public class UpdateHitBoxes {

    public static void updateCollisionBox1x1(Entity entity) {
        entity.getBoundingBoxComponent().clearHitBoxes();
        entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(
                TILE_SIZE - 1,
                TILE_SIZE - 1)));
    }

    public static void updateCollisionBox1x2(Entity entity) {
        entity.getBoundingBoxComponent().clearHitBoxes();
        entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(
                TILE_SIZE  * 2 - 1,
                TILE_SIZE - 1)));
    }

    public static void updateCollisionBox2x2(Entity entity) {
        entity.getBoundingBoxComponent().clearHitBoxes();
        entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(
                TILE_SIZE  * 2 - 1,
                TILE_SIZE * 2 - 1)));
    }

    public static void updateCollisionBox1x3(Entity entity) {
        entity.getBoundingBoxComponent().clearHitBoxes();
        entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(
                TILE_SIZE - 1,
                TILE_SIZE * 3 - 1)));
    }

    public static void updateCollisionBoxCoin(Entity entity) {
        entity.getBoundingBoxComponent().clearHitBoxes();
        entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(
                30,
                30)));
    }
}
