package fr.proneus.engine.gui;

import fr.proneus.engine.Game;
import fr.proneus.engine.graphic.Color;
import fr.proneus.engine.graphic.Graphics;
import fr.proneus.engine.graphic.shape.Circle;
import fr.proneus.engine.graphic.shape.Rectangle;
import fr.proneus.engine.input.MousePosition;

public class Slider extends Component {

    private float x, y, width, height;
    private int value, max;

    private Rectangle valueRect;
    private Rectangle underRect;

    private Circle circle;
    private boolean focus;

    public Slider(float x, float y, float width, float height, int value, int max) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.value = value;
        this.max = max;

        // Setup components
        this.valueRect = new Rectangle(x, y, width * ((float) value / (float) max), height, Color.WHITE, true);
        this.valueRect.applyCameraZoom(false);
        this.underRect = new Rectangle(x, y, width, height, Color.DARK_GRAY, true);
        this.underRect.applyCameraZoom(false);
        this.circle = new Circle(x + width * ((float) value / (float) max), y + height / 2,
                this.focus ? new Color(200, 200, 200) : Color.GRAY, height * 1.75f, true);
        this.circle.applyCameraZoom(false);

    }

    @Override
    public void update(Game game) {
        // Update components
        this.valueRect.setWidth(width * ((float) value / (float) max));
        this.circle.setX(x + width * ((float) value / (float) max));
        this.circle.setColor(this.focus ? new Color(200, 200, 200) : Color.GRAY);

        if (game.getInput().isMouseJustDown(0)) {
            MousePosition mouse = game.getInput().getMousePosition().toCameraPosition();
            this.focus = circle.interact(mouse.getX(), mouse.getY());
        }

        if (game.getInput().isMouseJustUp(0)) {
            if (focus)
                focus = false;
        }

    }

    @Override
    public void render(Game game, Graphics graphic) {
        graphic.draw(underRect);
        graphic.draw(valueRect);
        graphic.draw(circle);
    }

    @Override
    public void onMouseMove(Game game, float x, float y) {
        if (this.focus) {
            MousePosition mouse = game.getInput().getMousePosition().toCameraPosition();
            float mouseX = mouse.getX() - (int) this.x;
            this.value = (int) (mouseX / width * (float) max);

            value = value < 0 ? 0 : value > max ? max : value;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value < 0 ? 0 : value > max ? max : value;
    }

}
