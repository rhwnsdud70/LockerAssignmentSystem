package yu22112077.LockerAssignmentSystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import yu22112077.LockerAssignmentSystem.model.entity.Locker;
import yu22112077.LockerAssignmentSystem.repository.LockerRepository;

import java.util.Map;

@Component
public class LockerDataLoader implements CommandLineRunner {

    private final LockerRepository lockerRepository;

    public LockerDataLoader(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    @Override
    public void run(String... args) {
        if (lockerRepository.count() == 0) {
            Map<String, Integer> roomMap = Map.of(
                    "115", 130,
                    "116", 80,
                    "117", 50,
                    "123", 40,
                    "124", 60,
                    "220", 110,
                    "221", 60,
                    "322B", 40,
                    "322F", 20,
                    "323", 80
            );

            roomMap.forEach((room, count) -> {
                for (int i = 1; i <= count; i++) {
                    String code = room + "-" + i;
                    Locker locker = new Locker(code, room);
                    lockerRepository.save(locker);
                }
            });

            System.out.println("총 " + roomMap.values().stream().mapToInt(i -> i).sum() + "개의 사물함이 초기 생성되었습니다.");
        }
    }
}
