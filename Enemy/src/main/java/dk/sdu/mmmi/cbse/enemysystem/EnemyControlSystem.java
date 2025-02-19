package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemies.Enemy;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


public class EnemyControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

            for (Entity enemy : world.getEntities(Enemy.class)) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

                enemy.setX(enemy.getX() + changeX * 0.5);
                enemy.setY(enemy.getY() + changeY * 0.5);

                if (enemy.getX() < 0) {
                    enemy.setX(enemy.getX() - gameData.getDisplayWidth());
                }

                if (enemy.getX() > gameData.getDisplayWidth()) {
                    enemy.setX(enemy.getX() % gameData.getDisplayWidth());
                }

                if (enemy.getY() < 0) {
                    enemy.setY(enemy.getY() - gameData.getDisplayHeight());
                }

                if (enemy.getY() > gameData.getDisplayHeight()) {
                    enemy.setY(enemy.getY() % gameData.getDisplayHeight());
                }

            }

    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        return bullet;
    }
}
