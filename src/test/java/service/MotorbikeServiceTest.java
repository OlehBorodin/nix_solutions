package service;

import com.model.ManufacturerMotorbike;
import com.model.Motorbike;
import com.repository.MotorbikeRepository;
import com.service.MotorbikeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class MotorbikeServiceTest {
    private MotorbikeService target;
    private MotorbikeRepository motorbikeRepository;

    @BeforeEach
    void setUp() {
        motorbikeRepository = Mockito.mock(MotorbikeRepository.class);
        target = new MotorbikeService(motorbikeRepository);
    }

    @Test
    void createMotorbike_negativeCount() {
        final List<Motorbike> actual = target.createAndSaveMotorbike(-1);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createMotorbike_zeroCount() {
        final List<Motorbike> actual = target.createAndSaveMotorbike(0);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createMotorbikes() {
        final List<Motorbike> actual = target.createAndSaveMotorbike(5);
        Assertions.assertEquals(5, actual.size());
        Mockito.verify(motorbikeRepository, Mockito.times(5))
                .save(Mockito.any());
    }

    @Test
    void saveMotorbikes() {
        final boolean motorbike1 = target.saveMotorbike(Collections.emptyList());
        Assertions.assertFalse(motorbike1);
    }

    @Test
    void printAll() {
        List<Motorbike> motorbikes = List.of(createSimpleMotorbike(), createSimpleMotorbike());
        Mockito.when(motorbikeRepository.getAll()).thenReturn(motorbikes);
        target.printAll();
    }

    private Motorbike createSimpleMotorbike() {

        return new Motorbike("Model", BigDecimal.ONE, ManufacturerMotorbike.YAMAHA);
    }

    @Test
    void findOneById_null1() {
        final Motorbike expected = createSimpleMotorbike();
        Mockito.when(motorbikeRepository.getById("")).thenReturn(expected);
        final Motorbike actual = target.findOneById(null);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void findOneById_null2() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findOneById(null);
        Mockito.verify(motorbikeRepository).getById(captor.capture());
        Assertions.assertEquals("", captor.getValue());
    }


}
