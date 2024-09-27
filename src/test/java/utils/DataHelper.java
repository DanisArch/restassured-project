package utils;

import com.github.javafaker.Faker;
import dto.User;
import dto.Profile;
import dto.Experience;
import dto.Education;
import dto.Post;

import static java.time.LocalDateTime.now;

public class DataHelper {

    static Faker faker = new Faker();

    public static User createTestUser() {
        User user = new User();
        user.setName(Config.getConfig("name"));
        user.setEmail(Config.getConfig("email"));
        user.setPassword(Config.getConfig("password"));
        return user;
    }

    public static User createUser() {
        User user = new User();
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        return user;
    }

    public static Profile createProfile(int userID) {
        Profile profile = new Profile();
        profile.setUserId(userID);
        profile.setCompany(faker.company().name());
        profile.setLocation(faker.address().country());
        profile.setYear(faker.date().birthday(18,65));
        profile.setStatus(faker.job().title());
        profile.setSkills(faker.job().keySkills());
        return  profile;
    }

    public static Post createPost(int userID) {
        Post post = new Post();
        post.setUserId(userID);
        post.setTitle(faker.lorem().sentence(faker.random().nextInt(5, 10)));
        post.setText(faker.lorem().sentence(faker.random().nextInt(10, 30)));
        return post;
    }

    public static Education createEducation(int userID) {

        Education education = new Education();
        education.setUserId(userID);
        education.setSchool(faker.educator().secondarySchool());
        education.setDegree(faker.educator().course());
        education.setFieldOfStudy(faker.educator().course());
        return education;
    }

    public static Experience createExperience(int userID) {

        Experience experience = new Experience();
        experience.setUserId(userID);
        experience.setTitle(faker.educator().course());
        experience.setCompany(faker.company().name());
        experience.setLocation(faker.address().country());
        return experience;
    }
}