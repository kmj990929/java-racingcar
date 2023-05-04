package car.domain;

import car.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class RacingResult {

    private int max = 0;
    private List<Car> racingResult;
    private List<String> winnerCars;


    public RacingResult(List<Car> cars) {
        this.racingResult = cars;
        winnerCars = new ArrayList<>();
        racingMaxPosition();
        winnerConfirm();
    }

    private void setMax(int carPosition) {
        if (carPosition >= this.max) {
            this.max = carPosition;
        }
    }
    public void racingMaxPosition() {
        for (Car car : this.racingResult) {
            setMax(car.getPosition());
        }
    }

    private void addWinnerCar(Car car){
        if(car.getPosition() == this.max){
            this.winnerCars.add(car.getName());
        }
    }

    public void winnerConfirm(){
        for (Car car : this.racingResult) {
            addWinnerCar(car);
        }
    }

    public List<String> getWinnerCarsName(){
        return this.winnerCars;
    }
}