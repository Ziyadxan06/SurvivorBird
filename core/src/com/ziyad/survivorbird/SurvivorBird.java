package com.ziyad.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background;
	Texture bird;
	Texture bee1;
	Texture bee2;
	Texture bee3;
	float birdX;
	float birdY;
	int gameState;
	float velocity;
	float gravity;
	float enemyVelocity;
	int score = 0;
	int scoredEnemy = 0;
	BitmapFont font;
	BitmapFont font2;
	Random random;
	Circle birdCircle;

	int numberofEnemies = 4;
	float [] enemyX = new float[numberofEnemies];
	float [] enemyOffSet = new float[numberofEnemies];
	float [] enemyOffSet2 = new float[numberofEnemies];
	float [] enemyOffSet3 = new float[numberofEnemies];
	Circle[] enemyCircle1 = new Circle[numberofEnemies];
	Circle[] enemyCircle2 = new Circle[numberofEnemies];
	Circle[] enemyCircle3 = new Circle[numberofEnemies];


	float distance;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture("bird.png");
		bee1 = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");
		birdX = Gdx.graphics.getWidth() / 5;
		birdY = Gdx.graphics.getHeight() / 2;
		random = new Random();
		gameState = 0;
		velocity = 8;
		gravity = 1f;
		enemyVelocity = 10;
		distance = Gdx.graphics.getWidth() / 2;
		birdCircle = new Circle();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(5);

		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(10);

		for(int i = 0; i<numberofEnemies; i++){
			enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()- 200);
			enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distance;
			enemyCircle1[i] = new Circle();
			enemyCircle2[i] = new Circle();
			enemyCircle3[i] = new Circle();

		}

	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if(gameState == 1){

			if(enemyX[scoredEnemy] < Gdx.graphics.getWidth() / 5){
				score++;
				if(scoredEnemy < numberofEnemies - 1){
					scoredEnemy++;
				}else{
					scoredEnemy = 0;
				}
			}

			if(Gdx.input.justTouched()){
				velocity = -20;
			}

			for(int i = 0; i< numberofEnemies; i++){
				if(enemyX[i] < Gdx.graphics.getWidth() / 15){
					enemyX[i] = enemyX[i] + numberofEnemies * distance;
					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()- 200);
				}else{
					enemyX[i] = enemyX[i] - enemyVelocity;
				}

				batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				batch.draw(bee2, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet2[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				batch.draw(bee3, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet3[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);

				enemyCircle1[i].set(enemyX[i] + Gdx.graphics.getWidth() / 30, enemyOffSet[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
				enemyCircle2[i].set(enemyX[i] + Gdx.graphics.getWidth() / 30, enemyOffSet2[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
				enemyCircle3[i].set(enemyX[i] + Gdx.graphics.getWidth() / 30, enemyOffSet3[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);

			}



			if (birdY > 0) {
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}
		}else if(gameState == 0){
			if(Gdx.input.justTouched()){
				gameState = 1;
			}
		}else if(gameState == 2){
			font2.draw(batch, "Tap To Play Again", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

			if(Gdx.input.justTouched()){
				gameState = 1;
			}
			for(int i = 0; i<numberofEnemies; i++){
				enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
				enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
				enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()- 200);
				enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distance;
				enemyCircle1[i] = new Circle();
				enemyCircle2[i] = new Circle();
				enemyCircle3[i] = new Circle();
			}
			birdY = Gdx.graphics.getHeight() / 2;
			velocity = 0;
			score = 0;
			scoredEnemy = 0;

		}

		for(int i = 0 ; i<numberofEnemies; i++){
			if(Intersector.overlaps(birdCircle, enemyCircle1[i]) || Intersector.overlaps(birdCircle, enemyCircle2[i]) || Intersector.overlaps(birdCircle, enemyCircle3[i])){
				gameState = 2;
			}
		}

		font.draw(batch, String.valueOf("Your Score: " + score), 100, 200);
		batch.draw(bird, birdX, birdY, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
		birdCircle.set(birdX + Gdx.graphics.getWidth() / 30, birdY + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
		batch.end();
	}
	
	@Override
	public void dispose () {

	}
}
