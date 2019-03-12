package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * ModelSkytrap - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelSkytrap extends EntityModel {

    public Cuboid mouthBase;
    public Cuboid tongue01;
    public Cuboid petalN01;
    public Cuboid petalS01;
    public Cuboid petalE01;
    public Cuboid petalW01;
    public Cuboid petalN02;
    public Cuboid petalNFangs03;
    public Cuboid petalNFangs04;
    public Cuboid petalNFangs01;
    public Cuboid petalNFangs02;
    public Cuboid petalS02;
    public Cuboid petalSFangs03;
    public Cuboid petalSFangs04;
    public Cuboid petalSFangs01;
    public Cuboid petalSFangs02;
    public Cuboid petalE02;
    public Cuboid petalEFangs03;
    public Cuboid petalEFangs04;
    public Cuboid petalEFangs01;
    public Cuboid petalEFangs02;
    public Cuboid petalW02;
    public Cuboid petalWFangs03;
    public Cuboid petalWFangs04;
    public Cuboid petalWFangs01;
    public Cuboid petalWFangs02;
    public Cuboid tongue02;
    public Cuboid tongue03;

    public ModelSkytrap() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.petalWFangs02 = new Cuboid(this, 0, 50);
        this.petalWFangs02.setRotationPoint(-2.0F, -0.3F, -3.5F);
        this.petalWFangs02.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalE02 = new Cuboid(this, 0, 38);
        this.petalE02.setRotationPoint(0.0F, -0.3F, -9.7F);
        this.petalE02.addBox(-3.5F, -1.0F, -4.0F, 7, 2, 4, 0.0F);
        this.setRotateAngle(petalE02, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalW02 = new Cuboid(this, 0, 38);
        this.petalW02.setRotationPoint(0.0F, -0.3F, -9.7F);
        this.petalW02.addBox(-3.5F, -1.0F, -4.0F, 7, 2, 4, 0.0F);
        this.setRotateAngle(petalW02, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalW01 = new Cuboid(this, 0, 23);
        this.petalW01.setRotationPoint(6.5F, -2.5F, 0.0F);
        this.petalW01.addBox(-5.0F, -1.5F, -10.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(petalW01, -0.13962634015954636F, -1.5707963267948966F, 0.0F);
        this.petalNFangs01 = new Cuboid(this, 0, 50);
        this.petalNFangs01.mirror = true;
        this.petalNFangs01.setRotationPoint(2.0F, -0.3F, -3.5F);
        this.petalNFangs01.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalE01 = new Cuboid(this, 0, 23);
        this.petalE01.setRotationPoint(-6.5F, -2.5F, 0.0F);
        this.petalE01.addBox(-5.0F, -1.5F, -10.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(petalE01, -0.13962634015954636F, 1.5707963267948966F, 0.0F);
        this.petalSFangs02 = new Cuboid(this, 0, 50);
        this.petalSFangs02.setRotationPoint(-2.0F, -0.3F, -3.5F);
        this.petalSFangs02.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.tongue02 = new Cuboid(this, 46, 46);
        this.tongue02.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tongue02.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
        this.petalEFangs01 = new Cuboid(this, 0, 50);
        this.petalEFangs01.mirror = true;
        this.petalEFangs01.setRotationPoint(2.0F, -0.3F, -3.5F);
        this.petalEFangs01.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalN02 = new Cuboid(this, 0, 38);
        this.petalN02.setRotationPoint(0.0F, -0.3F, -9.7F);
        this.petalN02.addBox(-3.5F, -1.0F, -4.0F, 7, 2, 4, 0.0F);
        this.setRotateAngle(petalN02, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalNFangs04 = new Cuboid(this, 9, 41);
        this.petalNFangs04.setRotationPoint(-4.5F, -0.3F, -5.0F);
        this.petalNFangs04.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalWFangs04 = new Cuboid(this, 9, 41);
        this.petalWFangs04.setRotationPoint(-4.5F, -0.3F, -5.0F);
        this.petalWFangs04.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalNFangs03 = new Cuboid(this, 9, 41);
        this.petalNFangs03.setRotationPoint(4.5F, -0.3F, -5.0F);
        this.petalNFangs03.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalWFangs03 = new Cuboid(this, 9, 41);
        this.petalWFangs03.setRotationPoint(4.5F, -0.3F, -5.0F);
        this.petalWFangs03.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.tongue01 = new Cuboid(this, 44, 36);
        this.tongue01.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.tongue01.addBox(-1.5F, -6.0F, -1.5F, 3, 6, 3, 0.0F);
        this.petalWFangs01 = new Cuboid(this, 0, 50);
        this.petalWFangs01.mirror = true;
        this.petalWFangs01.setRotationPoint(2.0F, -0.3F, -3.5F);
        this.petalWFangs01.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.tongue03 = new Cuboid(this, 39, 47);
        this.tongue03.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.tongue03.addBox(-0.5F, -10.0F, -0.5F, 1, 10, 1, 0.0F);
        this.petalSFangs03 = new Cuboid(this, 9, 41);
        this.petalSFangs03.setRotationPoint(4.5F, -0.3F, -5.0F);
        this.petalSFangs03.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalNFangs02 = new Cuboid(this, 0, 50);
        this.petalNFangs02.setRotationPoint(-2.0F, -0.3F, -3.5F);
        this.petalNFangs02.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalSFangs04 = new Cuboid(this, 9, 41);
        this.petalSFangs04.setRotationPoint(-4.5F, -0.3F, -5.0F);
        this.petalSFangs04.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalEFangs02 = new Cuboid(this, 0, 50);
        this.petalEFangs02.setRotationPoint(-2.0F, -0.3F, -3.5F);
        this.petalEFangs02.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalS01 = new Cuboid(this, 0, 23);
        this.petalS01.setRotationPoint(0.0F, -2.5F, 6.5F);
        this.petalS01.addBox(-5.0F, -1.5F, -10.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(petalS01, -0.13962634015954636F, 3.141592653589793F, 0.0F);
        this.petalN01 = new Cuboid(this, 0, 23);
        this.petalN01.setRotationPoint(0.0F, -2.5F, -6.5F);
        this.petalN01.addBox(-5.0F, -1.5F, -10.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(petalN01, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalEFangs03 = new Cuboid(this, 9, 41);
        this.petalEFangs03.setRotationPoint(4.5F, -0.3F, -5.0F);
        this.petalEFangs03.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.petalS02 = new Cuboid(this, 0, 38);
        this.petalS02.setRotationPoint(0.0F, -0.3F, -9.7F);
        this.petalS02.addBox(-3.5F, -1.0F, -4.0F, 7, 2, 4, 0.0F);
        this.setRotateAngle(petalS02, -0.13962634015954636F, 0.0F, 0.0F);
        this.petalSFangs01 = new Cuboid(this, 0, 50);
        this.petalSFangs01.mirror = true;
        this.petalSFangs01.setRotationPoint(2.0F, -0.3F, -3.5F);
        this.petalSFangs01.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 0, 0.0F);
        this.petalEFangs04 = new Cuboid(this, 9, 41);
        this.petalEFangs04.setRotationPoint(-4.5F, -0.3F, -5.0F);
        this.petalEFangs04.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.mouthBase = new Cuboid(this, 0, 0);
        this.mouthBase.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.mouthBase.addBox(-7.0F, -4.0F, -7.0F, 14, 4, 14, 0.0F);
        this.petalW02.addChild(this.petalWFangs02);
        this.petalE01.addChild(this.petalE02);
        this.petalW01.addChild(this.petalW02);
        this.mouthBase.addChild(this.petalW01);
        this.petalN02.addChild(this.petalNFangs01);
        this.mouthBase.addChild(this.petalE01);
        this.petalS02.addChild(this.petalSFangs02);
        this.tongue01.addChild(this.tongue02);
        this.petalE02.addChild(this.petalEFangs01);
        this.petalN01.addChild(this.petalN02);
        this.petalN01.addChild(this.petalNFangs04);
        this.petalW01.addChild(this.petalWFangs04);
        this.petalN01.addChild(this.petalNFangs03);
        this.petalW01.addChild(this.petalWFangs03);
        this.petalW02.addChild(this.petalWFangs01);
        this.tongue02.addChild(this.tongue03);
        this.petalS01.addChild(this.petalSFangs03);
        this.petalN02.addChild(this.petalNFangs02);
        this.petalS01.addChild(this.petalSFangs04);
        this.petalE02.addChild(this.petalEFangs02);
        this.mouthBase.addChild(this.petalS01);
        this.mouthBase.addChild(this.petalN01);
        this.petalE01.addChild(this.petalEFangs03);
        this.petalS01.addChild(this.petalS02);
        this.petalS02.addChild(this.petalSFangs01);
        this.petalE01.addChild(this.petalEFangs04);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.tongue01.render(f5);
        this.mouthBase.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.rotationPointX = x;
        Cuboid.rotationPointY = y;
        Cuboid.rotationPointZ = z;
    }

}