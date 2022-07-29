package service;

import com.model.Bus;
import com.model.ManufacturerBus;
import com.repository.BusRepository;
import com.service.BusService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class BusServiceTest {
    private BusService target;
    private BusRepository busRepository;

    @BeforeEach
    void setUp() {
        busRepository = Mockito.mock(BusRepository.class);
        target = new BusService(busRepository);
    }

    @Test
    void createBuses_negativeCount() {
        final List<Bus> actual = target.createAndSaveVehicle(-1);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createBuses_zeroCount() {
        final List<Bus> actual = target.createAndSaveVehicle(0);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createBuses() {
        final List<Bus> actual = target.createAndSaveVehicle(5);
        Assertions.assertEquals(5, actual.size());
        Mockito.verify(busRepository, Mockito.times(5))
                .save(Mockito.any());
    }

    @Test
    void saveBuses() {
        final boolean bus1 = target.saveVehicle(Collections.emptyList());
        Assertions.assertFalse(bus1);
    }

    @Test
    void printAll() {
        List<Bus> buses = List.of(createSimpleBus(), createSimpleBus());
        Mockito.when(busRepository.getAll()).thenReturn(buses);
        target.printAll();
    }

    private Bus createSimpleBus() {
        return new Bus("New Model", 123,99,
                ManufacturerBus.ANKAI, BigDecimal.ONE, 1);
    }


    @Test
    void findOneById_null2() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findOneById(null);
        Mockito.verify(busRepository).findById(captor.capture());
        Assertions.assertEquals("", captor.getValue());
    }


}
