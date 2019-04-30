package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 *
*/
public abstract class Weapon {
	
    protected Bullets bulletType;
    protected int reloadTime;
    protected int damage;
    protected int numberOfBullets;
    protected int maxBullets;
    protected int firingRate;

	public Bullets getBulletType() {
		return bulletType;
	}

	public void setBulletType(Bullets bulletType) {
		this.bulletType = bulletType;
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getNumberOfBullets() {
		return numberOfBullets;
	}

	public void setNumberOfBullets(int numberOfBullets) {
		this.numberOfBullets = numberOfBullets;
	}

	public int getMaxBullets() {
		return maxBullets;
	}

	public void setMaxBullets(int maxBullets) {
		this.maxBullets = maxBullets;
	}

	public int getFiringRate() {
		return firingRate;
	}

	public void setFiringRate(int firingRate) {
		this.firingRate = firingRate;
	}
    
    

}

