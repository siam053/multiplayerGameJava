package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
public class AssaultWeapon extends Weapon{
	
	public AssaultWeapon() {
		//this.bulletType = new LargeBullet();
		this.damage = 20;
		this.firingRate = 5;
		this.maxBullets = 40;
		this.numberOfBullets = 0;
		this.reloadTime = 5;
	}

}
