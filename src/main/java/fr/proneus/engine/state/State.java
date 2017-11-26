package fr.proneus.engine.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.proneus.engine.Game;
import fr.proneus.engine.graphic.Graphics;
import fr.proneus.engine.gui.Component;
import fr.proneus.engine.light.LightManager;

public abstract class State {

	private List<Component> components = new ArrayList<>();
	private LightManager lightManager = new LightManager();

	public abstract void create(Game game);

	public abstract void update(Game game);

	public abstract void render(Game game, Graphics graphic);

	public abstract void exit(Game game);

	public void onKeyDown(Game game, int key, int scancode) {
	}

	public void onKeyUp(Game game, int key, int scancode) {
	}

	public void onKeyRepeat(Game game, int key, int scancode) {
	}

	public void onMouseMove(Game game, int x, int y) {
	}

	public void onMouseDown(Game game, int key) {
	}

	public void onMouseUp(Game game, int key) {
	}

	public void onMouseScroll(Game game, double power) {
	}

	public void onControllerConnect(Game game, int joy) {
	}

	public void onControllerDisconnect(Game game, int joy) {
	}

	// Discord
	public void onDiscordRPCReady(Game game) {
	}

	public void onDiscordRPCDisconnected(Game game) {
	}

	public void onDiscordRPCErrored(Game game) {
	}

	public void onDiscordRPCJoinGame(Game game) {
	}

	public void onDiscordRPCSpectateGame(Game game) {
	}

	public void onDiscordRPCJoinRequest(Game game) {
	}

	// Components

	public void componentsUpdate(Game game) {
		for (Component comp : components) {
			if (comp.isVisible())
				comp.update(game);
		}
	}

	public void componentsRender(Game game, Graphics graphic) {
		for (Component comp : components) {
			if (comp.isVisible())
				comp.render(game, graphic);
		}
	}

	public List<Component> getComponents() {
		return components;
	}

	public void addComponent(Component component) {
		components.add(component);
	}

	public void removeComponent(Component component) {
		Iterator<Component> iter = components.iterator();

		while (iter.hasNext()) {
			Component c = iter.next();

			if (c.equals(component)) {
				iter.remove();
				break;
			}
		}
	}

	public LightManager getLightManager() {
		return lightManager;
	}

}