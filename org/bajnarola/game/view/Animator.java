package org.bajnarola.game.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Animator {
	final static int TILE_PLACEMENT_DURATION = 50; //in frames (~2sec)
	final static int LANDSCAPE_GLOW_DURATION = 80; // (~5sec)
	final static int TILE_PROBE_GLOW_DURATION = 60; // (<2sec)
	final static int MEEPLE_PLACEMENT_DURATION = 50;
	final static int MEEPLE_REMOVAL_DURATION = 50;
	final static int SHOW_SCORE_DURATION = 50;
	
	final static int LG_GRADIENT_BEGIN_START = 0;
	final static int LG_GRADIENT_BEGIN_END = 30;
	final static int LG_GRADIENT_FINISH_START = 50;
	final static int LG_GRADIENT_FINISH_END = 80;
	
	final static float TILE_PLACEMENT_INITIAL_SCALE = (float)1.7;
	final static float TILE_PLACEMENT_FINAL_SCALE = (float)1;
	final static float LANDSCAPE_GLOW_INITIAL_GRADIENT = (float)0;
	final static float LANDSCAPE_GLOW_FINAL_GRADIENT = (float)0.3;
	final static float TILE_PROBE_GLOW_INITIAL_OPACITY = (float)0;
	final static float TILE_PROBE_GLOW_FINAL_OPACITY = (float)1;
	final static float MEEPLE_PLACEMENT_INITIAL_OFFSET = -300; //pixel
	final static float MEEPLE_PLACEMENT_FINAL_OFFSET = 0; //pixel
	final static float MEEPLE_PLACEMENT_INITIAL_OPACITY = 0; 
	final static float MEEPLE_PLACEMENT_FINAL_OPACITY = 1;
	final static float MEEPLE_REMOVAL_INITIAL_OFFSET = 0; //pixel
	final static float MEEPLE_REMOVAL_FINAL_OFFSET = -500; //pixel
	final static float MEEPLE_REMOVAL_INITIAL_OPACITY = 1;
	final static float MEEPLE_REMOVAL_FINAL_OPACITY = 0;
	final static float SHOW_SCORE_INITIAL_OFFSET = 0; //pixel
	final static float SHOW_SCORE_FINAL_OFFSET = -300; //pixel
	final static float SHOW_SCORE_INITIAL_OPACITY = 1;
	final static float SHOW_SCORE_FINAL_OPACITY = 0;
	
	int tilePlacementFrame;
	int landscapeGlowFrame;
	int tileProbeGlowFrame;
	int meeplePlacementFrame;
	int meepleRemovalFrame;
	int showScoreFrame;
	
	boolean tilePlacementOn;
	boolean landscapeGlowOn;
	boolean tileProbeGlowOn;
	boolean meeplePlacementOn;
	boolean meepleRemovalOn;
	boolean showScoreOn;
	
	Image blue;
	
	public Animator() throws SlickException{
		tilePlacementOn = landscapeGlowOn = tileProbeGlowOn = showScoreOn = meeplePlacementOn = meepleRemovalOn = false;
		tilePlacementFrame = landscapeGlowFrame = tileProbeGlowFrame = showScoreFrame = meeplePlacementFrame = meepleRemovalFrame = 0;
		blue = new Image("res/misc/blue.png");
	}
	
	private float getTilePlacementScale(){
		if(tilePlacementOn){
			return (((float)(TILE_PLACEMENT_FINAL_SCALE - TILE_PLACEMENT_INITIAL_SCALE) / (float)TILE_PLACEMENT_DURATION) * tilePlacementFrame) + TILE_PLACEMENT_INITIAL_SCALE;
		}
		return -1;
	}
	
	private float getLandscapeGlowGradient(){
		if(landscapeGlowOn){
			if(landscapeGlowFrame < LG_GRADIENT_BEGIN_END){
				return ((LANDSCAPE_GLOW_FINAL_GRADIENT - LANDSCAPE_GLOW_INITIAL_GRADIENT) / (LG_GRADIENT_BEGIN_END - LG_GRADIENT_BEGIN_START) * landscapeGlowFrame) + LANDSCAPE_GLOW_INITIAL_GRADIENT;
			} else if(landscapeGlowFrame >= LG_GRADIENT_FINISH_START){
				return ((LANDSCAPE_GLOW_FINAL_GRADIENT - LANDSCAPE_GLOW_INITIAL_GRADIENT) / (LG_GRADIENT_BEGIN_END - LG_GRADIENT_BEGIN_START) * (LG_GRADIENT_FINISH_END - landscapeGlowFrame)) + LANDSCAPE_GLOW_INITIAL_GRADIENT;
			} else
				return LANDSCAPE_GLOW_FINAL_GRADIENT;
		}
		return -1;
	}
	
	private float getTileProbeOpacity(){
		if(tileProbeGlowOn){
			if(tileProbeGlowFrame < (TILE_PROBE_GLOW_DURATION / 2))
				return (((TILE_PROBE_GLOW_FINAL_OPACITY - TILE_PROBE_GLOW_INITIAL_OPACITY) / (TILE_PROBE_GLOW_DURATION/2)) * tileProbeGlowFrame) + TILE_PROBE_GLOW_INITIAL_OPACITY;
			else
				return (((TILE_PROBE_GLOW_FINAL_OPACITY - TILE_PROBE_GLOW_INITIAL_OPACITY) / (TILE_PROBE_GLOW_DURATION/2)) * (TILE_PLACEMENT_DURATION - tileProbeGlowFrame)) + TILE_PROBE_GLOW_INITIAL_OPACITY;
		}
		return -1;
	}
	
	private int getMeeplePlacementYOffset(){
		if(meeplePlacementOn){
			return (int) ((((MEEPLE_PLACEMENT_FINAL_OFFSET - MEEPLE_PLACEMENT_INITIAL_OFFSET) / MEEPLE_PLACEMENT_DURATION) * meeplePlacementFrame) + MEEPLE_PLACEMENT_INITIAL_OFFSET);
		}
		return 0;
	}
	
	private float getMeeplePlacementAlpha(){
		if(meeplePlacementOn){
			return ((((MEEPLE_PLACEMENT_FINAL_OPACITY - MEEPLE_PLACEMENT_INITIAL_OPACITY) / MEEPLE_PLACEMENT_DURATION) * meeplePlacementFrame) + MEEPLE_PLACEMENT_INITIAL_OPACITY);
		}
		return 0;
	}
	
	private int getMeepleRemovalYOffset(){
		if(meepleRemovalOn){
			return (int) ((((MEEPLE_REMOVAL_FINAL_OFFSET - MEEPLE_REMOVAL_INITIAL_OFFSET) / MEEPLE_REMOVAL_DURATION) * meepleRemovalFrame) + MEEPLE_REMOVAL_INITIAL_OFFSET);
		}
		return 0;
	}
	
	private float getMeepleRemovalAlpha(){
		if(meepleRemovalOn){
			return ((((MEEPLE_REMOVAL_FINAL_OPACITY - MEEPLE_REMOVAL_INITIAL_OPACITY) / MEEPLE_REMOVAL_DURATION) * meepleRemovalFrame) + MEEPLE_REMOVAL_INITIAL_OPACITY);
		}
		return 0;
	}
	
	private int getShowScoreYOffset(){
		if(meepleRemovalOn){
			return (int) ((((SHOW_SCORE_FINAL_OFFSET - SHOW_SCORE_INITIAL_OFFSET) / SHOW_SCORE_DURATION) * showScoreFrame) + SHOW_SCORE_INITIAL_OFFSET);
		}
		return 0;
	}
	
	private float getShowScoreAlpha(){
		if(meepleRemovalOn){
			return ((((SHOW_SCORE_FINAL_OPACITY - SHOW_SCORE_INITIAL_OPACITY) / SHOW_SCORE_DURATION) * showScoreFrame) + SHOW_SCORE_INITIAL_OPACITY);
		}
		return 0;
	}
	
	public void drawShowScore(Graphics g, int value, int x, int y){
		//TODO: draw the string in a supersmart way
	}
	
	public void drawLandscapeGlowingTile(GraphicalTile tile, boolean zoomOutView, float scale){
		blue.setAlpha(getLandscapeGlowGradient());
		tile.draw(zoomOutView, scale, blue);
	}
	
	public void drawTilePlacement(GraphicalTile tile, boolean zoomOutView, float scale){
		tile.draw(zoomOutView, scale, getTilePlacementScale());
	}
	
	public void drawTileProbe(GraphicalTile tryEffect, boolean zoomOutView, float scale, boolean result){
		tryEffect.setAlpha(getTileProbeOpacity());
		if(result)
			tryEffect.draw(zoomOutView, scale, new Color(Color.green));
		else
			tryEffect.draw(zoomOutView, scale, new Color(Color.red));
	}
	
	public void drawMeeplePlacement(GraphicalMeeple meeple, boolean zoomOutView, float scale){
		meeple.displace(0, getMeeplePlacementYOffset());
		meeple.setAlpha(getMeeplePlacementAlpha());
		meeple.draw(zoomOutView, scale);
	}
	
	public void drawMeepleRemoval(GraphicalMeeple meeple, boolean zoomOutView, float scale){
		meeple.displace(0, getMeepleRemovalYOffset());
		meeple.setAlpha(getMeepleRemovalAlpha());
		meeple.draw(zoomOutView, scale);
	}
	
	public void step(){
		if(tilePlacementOn){
			tilePlacementFrame++;
			if(tilePlacementFrame > TILE_PLACEMENT_DURATION){
				tilePlacementOn = false;
			}
		}
		if(landscapeGlowOn){
			landscapeGlowFrame++;
			if(landscapeGlowFrame > LANDSCAPE_GLOW_DURATION){
				landscapeGlowOn = false;
			}
		}
		if(tileProbeGlowOn){
			tileProbeGlowFrame++;
			if(tileProbeGlowFrame > TILE_PROBE_GLOW_DURATION){
				tileProbeGlowOn = false;
			}
		}
		if(meeplePlacementOn){
			meeplePlacementFrame++;
			if(meeplePlacementFrame > MEEPLE_PLACEMENT_DURATION){
				meeplePlacementOn = false;
			}
		}
		if(meepleRemovalOn){
			meepleRemovalFrame++;
			if(meepleRemovalFrame > MEEPLE_REMOVAL_DURATION){
				meepleRemovalOn = false;
			}
		}
		if(showScoreOn){
			showScoreFrame++;
			if(showScoreFrame > SHOW_SCORE_DURATION)
				showScoreOn = false;
		}
	}
	
	public boolean isTilePlacementOn(){
		return tilePlacementOn;
	}
	
	public boolean isLandscapeGlowOn(){
		return landscapeGlowOn;
	}
	
	public boolean isTileProbeGlowOn(){
		return tileProbeGlowOn;
	}
	
	public boolean isMeeplePlacementOn(){
		return meeplePlacementOn;
	}
	
	public boolean isMeepleRemovalOn(){
		return meepleRemovalOn;
	}
	
	public boolean isShowScoreOn(){
		return showScoreOn;
	}
	
	public boolean automaticAnimationsEnded(){
		return !meeplePlacementOn && !meepleRemovalOn && !tilePlacementOn && !landscapeGlowOn && !showScoreOn;
	}
	
	public void enableTilePlacement(){
		tilePlacementFrame = 0;
		tilePlacementOn = true;
	}
	
	public void enableLandscapeGlow(){
		landscapeGlowFrame = 0;
		landscapeGlowOn = true;
	}
	
	public void enableTileProbeGlow(){
		tileProbeGlowFrame = 0;
		tileProbeGlowOn = true;
	}
	
	public void enableMeeplePlacement(){
		meeplePlacementFrame = 0;
		meeplePlacementOn = true;
	}
	
	public void enableMeepleRemoval(){
		meepleRemovalFrame = 0;
		meepleRemovalOn = true;
	}
	
	public void enableShowScore(){
		showScoreFrame = 0;
		showScoreOn = true;
	}
}
