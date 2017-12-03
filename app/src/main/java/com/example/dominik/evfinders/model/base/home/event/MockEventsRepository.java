package com.example.dominik.evfinders.model.base.home.event;

import com.example.dominik.evfinders.application.recommendation.EventsRecommendation;
import com.example.dominik.evfinders.application.recommendation.Recommendation;
import com.example.dominik.evfinders.command.CoordinateCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.database.pojo.Rating;
import com.example.dominik.evfinders.database.pojo.User;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;

import static io.reactivex.Observable.fromArray;

/**
 * Created by Dominik on 01.09.2017.
 */

public class MockEventsRepository implements IEventsRepository {

    private IPrefs prefs;
    private Recommendation systemRecomendation;

    @Inject
    public MockEventsRepository(IPrefs prefs) {
        this.prefs = prefs;
    }

    @Override
    public Single<Response<TaskResponse>> createEvent(EventCommand eventCommand) {
        return null;
    }

    @Override
    public  Single<Response<List<EventCommand>>> getEvents(CoordinateCommand coordinateCommand) {
        List<EventCommand> events = createMockEvents();


        return null;
    }

    @Override
    public Single<Response<List<EventCommand>>> getEventsWithRecommendation(CoordinateCommand coordinateCommand) {
        return null;
    }


    @Override
    public boolean removeUserKey() {
        return prefs.del(Prefs.API_KEY);
    }

    @Override
    public boolean removeFcmToken() {
        return false;
    }


    private List<EventCommand> createMockEvents() {
        User user = new User();
        user.setProfile(new int[]{1, 6, 6, 6, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        systemRecomendation = new EventsRecommendation(user);

        List<Rating> ratings = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Random random = new Random();
        List<Event> events = new ArrayList<>();
        Event koncertMetallica = new Event();
        koncertMetallica.setId((long) 1);
        koncertMetallica.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        koncertMetallica.setName("Koncert zespołu Metallica");

        koncertMetallica.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        koncertMetallica.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        koncertMetallica.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        koncertMetallica.setRatings(ratings);
        koncertMetallica.setUsersRegisteredToEvent(users);
        events.add(koncertMetallica);

        Event meczPolskiPilkaNozna = new Event();
        meczPolskiPilkaNozna.setId((long) 1);
        meczPolskiPilkaNozna.setDescription("Nula Stankiewicz (wcześniej występująca jako Anna Stankiewicz) to artystka znana przede wszystkim w kręgach piosenki literackiej. Zadebiutowała jako laureatka kilku konkursów poezji śpiewanej, ale szybko odkryła, że konkursy nie są zadowalającą ją formą. Występowała w wielu składach, ale od kilkunastu niemal lat, na stałe współpracuje z Januszem Stroblem. W 2013 r. wspólnie stworzyli dwupłytowy album „Strobel Kofta Wołek” (21 piosenek do słów Jonasza Kofty i Jana Wołka z muzyką Janusza Strobla, śpiewane przez Nulę Stankiewicz), który rozszedł się niemal natychmiast. Obecnie artyści wznawiają nakład, a jednocześnie pracują nad kolejnymi płytami. Cechą charakterystyczną Nuli jest zachowanie poetyckości i subtelności, w połączeniu z jazzowymi aranżacjami muzyków, z którymi współpracuje. Ta kompilacja to znak firmowy artystki. Obecnie dla Nuli piosenki piszą Jan Wołek, Magda Czapińska.");
        meczPolskiPilkaNozna.setName("Mecz Polska-Armenia");

        meczPolskiPilkaNozna.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        meczPolskiPilkaNozna.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        meczPolskiPilkaNozna.setProfileVector(new int[]{1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        meczPolskiPilkaNozna.setRatings(ratings);
        meczPolskiPilkaNozna.setUsersRegisteredToEvent(users);
        events.add(meczPolskiPilkaNozna);

        Event mecz = new Event();
        mecz.setId((long) 1);
        mecz.setDescription("Mecz ligowy. Siatkarze Bielska bialej zmierza sie z tegoo rocznymi mistrzami Polski Skra");
        mecz.setName("Mecz BBTS Bielsko biaa - Skra Belchatow");

        mecz.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        mecz.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        mecz.setProfileVector(new int[]{1, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        mecz.setRatings(ratings);
        mecz.setUsersRegisteredToEvent(users);
        events.add(mecz);

        Event filmWladcaPierscienia = new Event();
        filmWladcaPierscienia.setId((long) 1);
        filmWladcaPierscienia.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        filmWladcaPierscienia.setName("Film władca pierścienia");

        filmWladcaPierscienia.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        filmWladcaPierscienia.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        filmWladcaPierscienia.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 6, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0});
        filmWladcaPierscienia.setRatings(ratings);
        filmWladcaPierscienia.setUsersRegisteredToEvent(users);
        events.add(filmWladcaPierscienia);

        Event filmChlopakiNiePlacz = new Event();
        filmChlopakiNiePlacz.setId((long) 1);
        filmChlopakiNiePlacz.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        filmChlopakiNiePlacz.setName("Film chłopaki nie płaczą!");

        filmChlopakiNiePlacz.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        filmChlopakiNiePlacz.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        filmChlopakiNiePlacz.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        filmChlopakiNiePlacz.setRatings(ratings);
        filmChlopakiNiePlacz.setUsersRegisteredToEvent(users);

        events.add(filmChlopakiNiePlacz);

        Event koncertDiscoPop = new Event();
        koncertDiscoPop.setId((long) 1);
        koncertDiscoPop.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        koncertDiscoPop.setName("Koncert Disco polo i gwaizdy pop!");

        koncertDiscoPop.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        koncertDiscoPop.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        koncertDiscoPop.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 6, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        koncertDiscoPop.setRatings(ratings);
        koncertDiscoPop.setUsersRegisteredToEvent(users);
        events.add(koncertDiscoPop);

        Event koncert = new Event();
        koncert.setId((long) 1);
        koncert.setDescription("Plenerowy koncert grupy Dream Theatre!");
        koncert.setName("Koncert Dream Theatre");

        koncert.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        koncert.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        koncert.setProfileVector(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 1, 6, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        koncert.setRatings(ratings);
        koncert.setUsersRegisteredToEvent(users);
        events.add(koncert);

        Event bieganie = new Event();
        bieganie.setId((long) 1);
        bieganie.setDescription("Wydarzenie roku na Warszawskim Bemowie!");
        bieganie.setName("Biegaj dla zdrowia!");

        bieganie.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        bieganie.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        bieganie.setProfileVector(new int[]{1, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        bieganie.setRatings(ratings);
        bieganie.setUsersRegisteredToEvent(users);
        events.add(bieganie);

        Event bieganie2 = new Event();
        bieganie2.setId((long) 1);
        bieganie2.setDescription("Bieg po zdrowie! Wydarzenie charytatywne, zapraszamy wszystkich chetnych!");
        bieganie2.setName("Biegaj juz dzis z nami!");

        bieganie2.setLatituide(49.53 + (49.63 - 49.53) * random.nextDouble());
        bieganie2.setLongitude(19.065 + (19.1842 - 19.065) * random.nextDouble());
        bieganie2.setProfileVector(new int[]{1, 6, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
       bieganie2.setRatings(ratings);
       bieganie2.setUsersRegisteredToEvent(users);
        events.add(bieganie2);

        return null;

    }
}