package racingcar.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.car.Car;
import racingcar.car.Winners;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WinnerDecisionByBigLocationsTest {

    private final WinnerDecisionStrategy winnerDecisionStrategy = new WinnerDecisionByBigLocations();

    @Test
    void 위치가_동일한_차들이_주어지는경우_주어진_차들을_모두_우승자로_리턴한다() {
        List<Car> cars = makeCarsWith(
                new Car("def", 0),
                new Car("aaa", 0),
                new Car("abc", 0)
        );

        Winners winners = winnerDecisionStrategy.decideWinners(cars);
        List<String> winnerNames = getWinnerNames(winners);

        assertThat(winnerNames).containsExactlyInAnyOrder("abc", "def", "aaa");
    }


    @Test
    void 위치가_가장_큰_차를_우승자로_리턴한다() {
        String winnerName = "abc";
        List<Car> cars = makeCarsWith(
                new Car("def", 0),
                new Car("aaa", 1),
                new Car(winnerName, 2)
        );

        Winners winners = winnerDecisionStrategy.decideWinners(cars);
        List<String> winnerNames = getWinnerNames(winners);

        assertThat(winnerNames).containsExactlyInAnyOrder(winnerName);
    }

    private List<Car> makeCarsWith(Car... cars) {
        return Arrays.stream(cars).collect(Collectors.toList());
    }

    private List<String> getWinnerNames(Winners winners) {
        return winners.winners().stream()
                .map(Car::name)
                .collect(Collectors.toList());
    }
}