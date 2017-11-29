package fr.proneus.engine.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import fr.proneus.engine.Game;
import fr.proneus.engine.gui.Component;
import fr.proneus.engine.state.State;

public class MousePositionManager extends GLFWCursorPosCallback {

    protected float x, y;
    private Game game;
	private State state;

	public MousePositionManager(Game game) {
		this.game = game;
	}

	@Override
	public void invoke(long window, double x, double y) {
		this.x = (int) x;
		this.y = (int) y;

        MousePosition mouse = game.getInput().getMousePosition();

		state.onMouseMove(game, mouse.getX(), mouse.getY());
		for (Component comp : state.getComponents()) {
			if (comp.isVisible())
				comp.onMouseMove(game, mouse.getX(), mouse.getY());
		}
	}

	public void setListener(State state) {
		this.state = state;

	}

}
