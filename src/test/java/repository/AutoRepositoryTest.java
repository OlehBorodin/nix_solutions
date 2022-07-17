package repository;

import com.model.Auto;
import com.model.BodyType;
import com.model.ManufacturerAuto;
import com.repository.AutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class AutoRepositoryTest {
    private  AutoRepository target;

    private  Auto auto;

    @BeforeEach
     void setUp() {
        target = new AutoRepository();
        auto = createSimpleAuto();
        target.save(auto);
    }

    private Auto createSimpleAuto(){
        return new Auto("M5", BigDecimal.valueOf(30000),ManufacturerAuto.BMW, BodyType.SEDAN);
    }

    @Test
    void getById_findOne() {
        final Auto actual = target.findById(auto.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(auto.getId(), actual.getId());
    }

    @Test
    void getById_notFind() {
        final Auto actual = target.findById("007");
        Assertions.assertNull(actual);
    }

    @Test
    void getById_findOne_manyAutos() {
        final Auto auto1 = createSimpleAuto();
        target.save(auto1);
        final  Auto actual = target.findById(auto.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(auto.getId(), actual.getId());
        Assertions.assertNotEquals(actual.getId(), auto1.getId());
    }

    @Test
    void getAll() {
        final List<Auto> actual = target.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());

    }
    @Test
    void save_success_notChangePrice() {
        auto.setPrice(BigDecimal.ONE);
        final boolean actual = target.save(auto);
        Assertions.assertTrue(actual);
        final Auto actualAuto = target.findById(auto.getId());
        Assertions.assertEquals(BigDecimal.ONE, actualAuto.getPrice());
    }

    @Test
    void save_fail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
    }
    @Test
    void save_success_changePrice() {
        final Auto actual = target.findById(auto.getId());
        Assertions.assertEquals(BigDecimal.valueOf(30000), actual.getPrice());
    }

    @Test
    void saveAll_null() {
        final boolean actual = target.saveAll(null);
        Assertions.assertFalse(actual);
    }

    @Test
    void saveAll_emptyList() {
        final boolean actual = target.saveAll(Collections.emptyList());
        Assertions.assertFalse(actual);
    }

    @Test
    void saveAll() {
        final boolean actual = target.saveAll(List.of(createSimpleAuto()));
        Assertions.assertTrue(actual);
    }

    @Test
    void update_notFound() {
        final Auto auto1 = createSimpleAuto();
        final boolean actual = target.update(auto1);
        Assertions.assertFalse(actual);
    }

    @Test
    void update() {
        auto.setPrice(BigDecimal.TEN);
        final boolean actual = target.update(auto);
        Assertions.assertTrue(actual);
        final Auto actualAuto = target.findById(auto.getId());
        Assertions.assertEquals(BigDecimal.TEN, actualAuto.getPrice());
    }


    @Test
    void delete() {
        // do it
    }
}
