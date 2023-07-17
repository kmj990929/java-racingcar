package study.step3.domain;

import study.step3.domain.strategy.MoveStrategy;
import study.step3.domain.strategy.RandomMoveStrategy;

public class Car {
    private int position;

    private final MoveStrategy moveStrategy;

    public Car() {
        this.moveStrategy = new RandomMoveStrategy();
    }

    public int getPosition() {
        return position;
    }

    public void move() {
        if (moveStrategy.canMove()) {
            this.position++;
        }
    }
}