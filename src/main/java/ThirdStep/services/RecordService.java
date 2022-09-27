package ThirdStep.services;

import ThirdStep.RandomMovingCondition;
import ThirdStep.model.Car;
import ThirdStep.model.RecordByCar;
import ThirdStep.model.RecordByRound;
import ThirdStep.utils.TextPrintUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RecordService {

    private static final String LOCATION_SIGN = "-";
    private static final String WINNER_DELIMITER = ", ";
    private static final int START_LOCATION = 0;

    public void printRecord(List<RecordByRound> recordByRounds) {
        recordByRounds.forEach(recordByRound -> {
            printByRound(recordByRound);
            TextPrintUtils.println("");
        });
    }

    private void printByRound(RecordByRound recordByRound) {
        recordByRound
                .getRecordByCars()
                .forEach(record ->
                    TextPrintUtils.println(String.format("%s : %s", record.getCar().getName(), LOCATION_SIGN.repeat(record.getLocation())))
                );
    }

    public void announceWinner(List<RecordByRound> recordByRounds) {
        printWinner(recordByRounds.get(recordByRounds.size() - 1));
    }

    public void printWinner(RecordByRound recordByRound) {
        List<Car> winners = getWinners(recordByRound.getRecordByCars());
        String winnerNames = winners
                .stream()
                .map(Car::getName)
                .collect(Collectors.joining(WINNER_DELIMITER));
        TextPrintUtils.println(String.format("%s가 최종 우승했습니다.", winnerNames));
    }

    private List<Car> getWinners(List<RecordByCar> recordByCars) {
        List<Integer> locations = recordByCars.stream().map(RecordByCar::getLocation).collect(Collectors.toList());
        int max = Collections.max(locations);

        return recordByCars
                .stream()
                .filter(recordByCar -> recordByCar.getLocation() == max)
                .map(RecordByCar::getCar)
                .collect(Collectors.toList());
    }

    public List<RecordByCar> initRecordByCars(List<Car> cars) {
        return cars.stream()
                .map(car -> new RecordByCar(car, START_LOCATION))
                .collect(Collectors.toList());
    }

    public List<RecordByCar> generateRecordByCar(List<RecordByCar> recordByCars) {
        return recordByCars
                .stream()
                .map(recordByCar -> new RecordByCar(
                        recordByCar.getCar(),
                        recordByCar.getLocation() + CarService.movedDistance(RandomMovingCondition.create()))
                )
                .collect(Collectors.toList());
    }
}