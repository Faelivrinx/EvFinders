package com.example.dominik.evfinders.model.base.home.profile;

import android.support.annotation.NonNull;

import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;
import com.example.dominik.evfinders.model.api.ProfileService;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Dominik on 07.11.2017.
 */

public class ProfileRepository implements IProfileRepository {

    private IPrefs prefs;
    private ProfileService service;

    @Inject
    public ProfileRepository(IPrefs prefs, ProfileService service) {
        this.prefs = prefs;
        this.service = service;
    }

    @Override
    public List<ProfileItem> getAllProfiles() {
        return createProfiles();
    }

    @Override
    public ProfileItem getProfileById(Long id) {
        return null;
    }

    @Override
    public Single<Response<TaskResponse>> saveProfile(String profile) {
        return service.saveOrUpdate(prefs.get(Prefs.API_KEY), profile);
    }

    private List<ProfileItem> createProfiles() {
        List<ProfileItem> profiles = createSportProfile();
        profiles.addAll(createMusicProfile());
        profiles.addAll(createCultureProfile());

        return profiles;
    }

    @NonNull
    private List<ProfileItem> createSportProfile() {
        List<ProfileItem> profiles = new ArrayList<>();
        ProfileItem pilkaNozna = new ProfileItem();
        pilkaNozna.setId(1L);
        pilkaNozna.setName("Piłka Nożna");
        pilkaNozna.setRating(0);

        ProfileItem siatkowka = new ProfileItem();
        siatkowka.setId(2L);
        siatkowka.setName("Siatkówka");
        siatkowka.setRating(0);

        ProfileItem koszykowka = new ProfileItem();
        koszykowka.setId(3L);
        koszykowka.setName("Koszykówka");
        koszykowka.setRating(0);

        ProfileItem bieganie = new ProfileItem();
        bieganie.setId(4L);
        bieganie.setName("Bieganie");
        bieganie.setRating(0);

        ProfileItem plywanie = new ProfileItem();
        plywanie.setId(5L);
        plywanie.setName("Pływanie");
        plywanie.setRating(0);

        ProfileItem rower = new ProfileItem();
        rower.setId(6L);
        rower.setName("Rower");
        rower.setRating(0);

        ProfileItem hokej = new ProfileItem();
        hokej.setId(7L);
        hokej.setName("Hokej");
        hokej.setRating(0);

        ProfileItem tenis = new ProfileItem();
        tenis.setId(8L);
        tenis.setName("Tenis");
        tenis.setRating(0);

        ProfileItem tenisStolowy = new ProfileItem();
        tenisStolowy.setId(9L);
        tenisStolowy.setName("Tenis Stołowy");
        tenisStolowy.setRating(0);

        ProfileItem rugby = new ProfileItem();
        rugby.setId(10L);
        rugby.setName("Rugby");
        rugby.setRating(0);

        ProfileItem joga = new ProfileItem();
        joga.setId(11L);
        joga.setName("Joga");
        joga.setRating(0);

        profiles.add(pilkaNozna);
        profiles.add(siatkowka);
        profiles.add(koszykowka);
        profiles.add(bieganie);
        profiles.add(plywanie);
        profiles.add(rower);
        profiles.add(hokej);
        profiles.add(tenis);
        profiles.add(tenisStolowy);
        profiles.add(rugby);
        profiles.add(joga);
        return profiles;
    }

    private List<ProfileItem> createMusicProfile() {
        List<ProfileItem> profiles = new ArrayList<>();
        ProfileItem rock = new ProfileItem();
        rock.setId(13L);
        rock.setName("Rock");
        rock.setRating(0);

        ProfileItem pop = new ProfileItem();
        pop.setId(14L);
        pop.setName("Pop");
        pop.setRating(0);

        ProfileItem metal = new ProfileItem();
        metal.setId(15L);
        metal.setName("Metal");
        metal.setRating(0);

        ProfileItem hipHop = new ProfileItem();
        hipHop.setId(16L);
        hipHop.setName("Hip-hop");
        hipHop.setRating(0);

        ProfileItem discoPolo = new ProfileItem();
        discoPolo.setId(17L);
        discoPolo.setName("Disco-Polo");
        discoPolo.setRating(0);

        ProfileItem techno = new ProfileItem();
        techno.setId(18L);
        techno.setName("Techno");
        techno.setRating(0);

        ProfileItem country = new ProfileItem();
        country.setId(19L);
        country.setName("Country");
        country.setRating(0);

        ProfileItem filmowa = new ProfileItem();
        filmowa.setId(20L);
        filmowa.setName("Filmowa");
        filmowa.setRating(0);

        ProfileItem powazna = new ProfileItem();
        powazna.setId(21L);
        powazna.setName("Poważna");
        powazna.setRating(0);

        ProfileItem jazz = new ProfileItem();
        jazz.setId(22L);
        jazz.setName("Jazz");
        jazz.setRating(0);

        profiles.add(rock);
        profiles.add(pop);
        profiles.add(metal);
        profiles.add(hipHop);
        profiles.add(discoPolo);
        profiles.add(techno);
        profiles.add(country);
        profiles.add(filmowa);
        profiles.add(powazna);
        profiles.add(jazz);

        return profiles;
    }

    private List<ProfileItem> createCultureProfile() {
        List<ProfileItem> profiles = new ArrayList<>();
        ProfileItem teatr = new ProfileItem();
        teatr.setId(24L);
        teatr.setName("Teatr");
        teatr.setRating(0);

        ProfileItem kino = new ProfileItem();
        kino.setId(25L);
        kino.setName("Kino");
        kino.setRating(0);

        ProfileItem taniec = new ProfileItem();
        taniec.setId(26L);
        taniec.setName("Taniec");
        taniec.setRating(0);

        ProfileItem sztuka = new ProfileItem();
        sztuka.setId(27L);
        sztuka.setName("Sztuka");
        sztuka.setRating(0);

        ProfileItem edukacja = new ProfileItem();
        edukacja.setId(28L);
        edukacja.setName("Edukacja");
        edukacja.setRating(0);

        ProfileItem dlaDzieci = new ProfileItem();
        dlaDzieci.setId(29L);
        dlaDzieci.setName("Dla dzieci");
        dlaDzieci.setRating(0);

        ProfileItem literatura = new ProfileItem();
        literatura.setId(30L);
        literatura.setName("Literatura");
        literatura.setRating(0);


        profiles.add(teatr);
        profiles.add(kino);
        profiles.add(taniec);
        profiles.add(sztuka);
        profiles.add(edukacja);
        profiles.add(dlaDzieci);
        profiles.add(literatura);

        return profiles;
    }
}
