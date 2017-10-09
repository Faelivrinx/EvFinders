package com.example.dominik.evfinders.model.base.home;

import com.example.dominik.evfinders.database.pojo.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Single;

import static io.reactivex.Observable.fromArray;

/**
 * Created by Dominik on 01.09.2017.
 */

public class MockMapRepository implements IMapRepository {


    @Override
    public Single<List<Event>> getEvents() {
        List<Event> events = createMockEvents();


        return Single.fromObservable(fromArray(events));
    }


    @Override
    public boolean removeUserKey() {
        return false;
    }

    @Override
    public boolean removeFcmToken() {
        return false;
    }


    private List<Event> createMockEvents() {
        Random random = new Random();
        List<Event> events = new ArrayList<>();
        Event koncertMetallica = new Event();
        koncertMetallica.setId((long) 1);
        koncertMetallica.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        koncertMetallica.setName("Koncert zespołu Metallica");

        koncertMetallica.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        koncertMetallica.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        koncertMetallica.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        events.add(koncertMetallica);

        Event meczPolskiPilkaNozna = new Event();
        meczPolskiPilkaNozna.setId((long) 1);
        meczPolskiPilkaNozna.setDescription("Mecz Polski w piłce nożnej z Armenią!");
        meczPolskiPilkaNozna.setName("Mecz Polska-Armenia");

        meczPolskiPilkaNozna.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        meczPolskiPilkaNozna.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        meczPolskiPilkaNozna.setProfileVector(new int[]{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        events.add(meczPolskiPilkaNozna);

        Event filmWladcaPierscienia = new Event();
        filmWladcaPierscienia.setId((long) 1);
        filmWladcaPierscienia.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        filmWladcaPierscienia.setName("Film władca pierścienia");

        filmWladcaPierscienia.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        filmWladcaPierscienia.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        filmWladcaPierscienia.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0});
        events.add(filmWladcaPierscienia);

        Event filmChlopakiNiePlacz = new Event();
        filmChlopakiNiePlacz.setId((long) 1);
        filmChlopakiNiePlacz.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        filmChlopakiNiePlacz.setName("Film chłopaki nie płaczą!");

        filmChlopakiNiePlacz.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        filmChlopakiNiePlacz.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        filmChlopakiNiePlacz.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        events.add(filmChlopakiNiePlacz);

        Event koncertDiscoPop = new Event();
        koncertDiscoPop.setId((long) 1);
        koncertDiscoPop.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        koncertDiscoPop.setName("Koncert Disco polo i gwaizdy pop!");

        koncertDiscoPop.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        koncertDiscoPop.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        koncertDiscoPop.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        events.add(koncertDiscoPop);

        Event bieganie = new Event();
        bieganie.setId((long) 1);
        bieganie.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        bieganie.setName("Biegaj dla zdrowia!");

        bieganie.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        bieganie.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        bieganie.setProfileVector(new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        events.add(bieganie);


        return events;
    }
}