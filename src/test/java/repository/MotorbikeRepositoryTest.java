package repository;


import com.model.ManufacturerMotorbike;
import com.model.Motorbike;
import com.repository.MotorbikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MotorbikeRepositoryTest {
    private MotorbikeRepository target;

    private Motorbike motorbike;

    @BeforeEach
    void setUp() {
        target = new MotorbikeRepository();
        motorbike = createSimpleMotorbike();
        target.save(motorbike);
    }

    private Motorbike createSimpleMotorbike() {

        return new Motorbike("Good Motorbike", BigDecimal.valueOf(1234), ManufacturerMotorbike.KAWASAKI);
    }

    @Test
    void getById_findOne() {
        final Motorbike motorbike1 = target.findById(motorbike.getId()).get();
        Assertions.assertNotNull(motorbike1);
        Assertions.assertEquals(motorbike.getId(), motorbike1.getId());
    }


    @Test
    void getById_findOne_manyAutos() {
        final Motorbike motorbike2 = createSimpleMotorbike();
        target.save(motorbike2);
        final Motorbike motorbike1 = target.findById(motorbike.getId()).get();
        Assertions.assertNotNull(motorbike1);
        Assertions.assertEquals(motorbike.getId(), motorbike1.getId());
        Assertions.assertNotEquals(motorbike2.getId(), motorbike1.getId());
    }

    @Test
    void getAll() {
        final List<Motorbike> motorbikes1 = target.getAll();
        Assertions.assertNotNull(motorbikes1);
        Assertions.assertEquals(1, motorbikes1.size());
    }

    @Test
    void save_success_notChangePrice() {
        motorbike.setPrice(BigDecimal.ONE);
        final boolean motorbike1 = target.save(motorbike);
        Assertions.assertTrue(motorbike1);
        final Motorbike newMotorbike = target.findById(motorbike.getId()).get();
        Assertions.assertEquals(BigDecimal.ONE, newMotorbike.getPrice());
    }

    @Test
    void save_fail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
    }

    @Test
    void save_success_changePrice() {
        target.save(motorbike);
        final Motorbike motorbike1 = target.findById(motorbike.getId()).get();
        Assertions.assertEquals(BigDecimal.valueOf(1234), motorbike1.getPrice());
    }

    @Test
    void saveAll_null() {
        final boolean motorbike1 = target.saveAll(null);
        Assertions.assertFalse(motorbike1);
    }

    @Test
    void saveAll_emptyList() {
        final boolean motorbike1 = target.saveAll(Collections.emptyList());
        Assertions.assertFalse(motorbike1);
    }

    @Test
    void saveAll() {
        final boolean motorbike1 = target.saveAll(List.of(createSimpleMotorbike()));
        Assertions.assertTrue(motorbike1);
    }

    @Test
    void update_notFound() {
        final Motorbike newMotorbike = createSimpleMotorbike();
        final boolean motorbike1 = target.update(newMotorbike);
        Assertions.assertFalse(motorbike1);
    }

    @Test
    void update() {
        motorbike.setPrice(BigDecimal.TEN);
        final boolean motorbike1 = target.update(motorbike);
        final Motorbike motorbike2 = target.findById(motorbike.getId()).get();
        Assertions.assertEquals(BigDecimal.TEN, motorbike2.getPrice());
    }

    @Test
    void delete() {
        MotorbikeRepository motorbike1 = mock(MotorbikeRepository.class);
        try {
            Mockito.doThrow(new IllegalArgumentException("Delete error"))
                    .when(motorbike1)
                    .delete("123");
            motorbike1.delete("");
        } catch (Exception e) {
            System.out.println("ID is empty");
        }
    }
}

