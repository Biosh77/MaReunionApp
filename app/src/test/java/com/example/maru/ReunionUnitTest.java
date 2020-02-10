package com.example.maru;

import com.example.maru.api.ApiService;
import com.example.maru.di.Injection;
import com.example.maru.model.Reunion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class ReunionUnitTest {

    private ApiService service;
    private Reunion reunion1;
    private Reunion reunion2;
    private Reunion reunion3;


    @Before
    public void setup() {
        service = Injection.getNewInstanceApiService();
        reunion1 = new Reunion("20h00", "Salle B", "peach", Arrays.asList("Meunier.jordane@gmail.com"), "10/02/2020");
        reunion2 = new Reunion("16h00","Salle A","Apple",Arrays.asList("Meunier.jordane@gmail.com"),"22/10/2020");
        reunion3 = new Reunion("17h00","Salle B","Kiwi",Arrays.asList("Meunier.jordane@gmail.com"),"15/02/2020");
    }

    @Test
    public void addReunionWithSuccess() {
        service.addReunion(reunion1);
        assertTrue(service.getReunions().contains(reunion1));
    }

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> expectedReunion = new ArrayList<>();
        expectedReunion.add(reunion1);
        service.addReunion(reunion1);
        assertEquals(expectedReunion, service.getReunions());
    }

    @Test
    public void deleteReunionWithSuccess() {
        service.addReunion(reunion1);
        assertTrue(service.getReunions().contains(reunion1));

        service.deleteReunion(reunion1);
        assertFalse(service.getReunions().contains(reunion1));
    }
    @Test
    public void filterReunionWithSuccess(){
        service.addReunion(reunion1);
        service.addReunion(reunion2);
        service.addReunion(reunion3);
        assertEquals(1,service.filterReunion("Salle A","22/10/2020").size());
        assertEquals(1,service.filterReunion("Salle B","10/02/2020").size());
        assertEquals(2,service.filterReunion("Salle B","").size());

    }
}