package repository;

import com.model.Bus;

import com.model.ManufacturerBus;
import com.repository.BusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;

public class BusRepositoryTest {
    private BusRepository target;

    private Bus bus;

    @BeforeEach
    void setUp() {
        target = new BusRepository();
        bus = createSimpleBus();
        target.save(bus);
    }

    private Bus createSimpleBus() {

        return new Bus("Mega Bus", 135, 99,
                ManufacturerBus.ANKAI, BigDecimal.valueOf(45674));
    }

    @Test
    void getById_findOne() {
        final Bus bus1 = target.findById(bus.getId()).get();
        Assertions.assertNotNull(bus1);
        Assertions.assertEquals(bus.getId(), bus1.getId());
    }


    @Test
    void getById_findOne_manyAutos() {
        final Bus bus2 = createSimpleBus();
        target.save(bus2);
        final Bus bus1 = target.findById(bus.getId()).get();
        Assertions.assertNotNull(bus1);
        Assertions.assertEquals(bus.getId(), bus1.getId());
        Assertions.assertNotEquals(bus2.getId(), bus1.getId());
    }

    @Test
    void getAll() {
        final List<Bus> bus1 = target.getAll();
        Assertions.assertNotNull(bus1);
        Assertions.assertEquals(1, bus1.size());
    }

    @Test
    void save_success_notChangePrice() {
        bus.setPrice(BigDecimal.ONE);
        final boolean bus1 = target.save(bus);
        Assertions.assertTrue(bus1);
        final Bus bus3 = target.findById(bus.getId()).get();
        Assertions.assertEquals(BigDecimal.ONE, bus3.getPrice());
    }

    @Test
    void save_fail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
    }

    @Test
    void save_success_changePrice() {
        BigDecimal currentPrice = bus.getPrice();
        bus.setPrice(BigDecimal.valueOf(1234));
        target.save(bus);
        final Bus bus1 = target.findById(bus.getId()).get();
        Assertions.assertEquals(BigDecimal.valueOf(1234), bus1.getPrice());
        Assertions.assertNotEquals(currentPrice, bus1.getPrice());
    }

    @Test
    void saveAll_null() {
        final boolean bus1 = target.saveAll(null);
        Assertions.assertFalse(bus1);
    }

    @Test
    void saveAll_emptyList() {
        final boolean bus1 = target.saveAll(Collections.emptyList());
        Assertions.assertFalse(bus1);
    }

    @Test
    void saveAll() {
        final boolean bus1 = target.saveAll(List.of(createSimpleBus()));
        Assertions.assertTrue(bus1);
    }

    @Test
    void update_notFound() {
        final Bus bus2 = createSimpleBus();
        final boolean bus1 = target.update(bus2);
        Assertions.assertFalse(bus1);
    }

    @Test
    void update() {
        bus.setPrice(BigDecimal.TEN);
        final boolean bus1 = target.update(bus);
        Assertions.assertTrue(bus1);
        final Bus bus2 = target.findById(bus.getId()).get();
        Assertions.assertEquals(BigDecimal.TEN, bus2.getPrice());
    }

    @Test
    void delete() {
        BusRepository bus1 = mock(BusRepository.class);
        try {
            Mockito.doThrow(new IllegalArgumentException("Delete error"))
                    .when(bus1)
                    .delete(bus.getId());
            bus1.delete("");
        } catch (Exception e) {
            System.out.println("ID is empty");
        }

    }
}
