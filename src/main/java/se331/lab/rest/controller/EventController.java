package se331.lab.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Event;


import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    List<Event> eventList;

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = null;
        for (Event event : eventList) {
            if (event.getId().equals(id)) {
            output = event;
            break;
            }
        }
        if (output != null) {
            return ResponseEntity.ok(output);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();

        eventList.add(Event.builder()
                .id(123L)
                .category("animal welfare")
                .title("Cat Adoption Day")
                .description("Find your new feline friend at this event.")
                .location("Meow Town")
                .date("January 28, 2022")
                .time("12:00")
                .petsAllowed(true)
                .organizer("Kat Laydee")
                .build());

        eventList.add(Event.builder()
                .id(456L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .date("March 14, 2022")
                .time("10:00")
                .petsAllowed(true)
                .organizer("Fern Pollin")
                .build());

        //db.json
        eventList.add(Event.builder()
                .id(5928101L)
                .category("animal welfare")
                .title("Cat Adoption Day")
                .description("Find your new feline friend at this event.")
                .location("Meow Town")
                .date("January 28, 2022")
                .time("12:00")
                .petsAllowed(true)
                .organizer("Kat Laydee")
                .build());

        eventList.add(Event.builder()
                .id(4582797L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .date("March 14, 2022")
                .time("10:00")
                .petsAllowed(true)
                .organizer("Fern Pollin")
                .build());

        eventList.add(Event.builder()
                .id(8419988L)
                .category("sustainability")
                .title("Beach Cleanup")
                .description("Help pick up trash along the shore.")
                .location("Playa Del Carmen")
                .date("July 22, 2022")
                .time("11:00")
                .petsAllowed(false)
                .organizer("Carey Wales")
                .build());

        eventList.add(Event.builder()
                .id(123456L)
                .category("gaming")
                .title("league of legends")
                .description("The good MOBA game.")
                .location("CMU")
                .date("July 25, 2022")
                .time("12:00")
                .petsAllowed(false)
                .organizer("Riot")
                .build());

        eventList.add(Event.builder()
                .id(456123L)
                .category("gaming")
                .title("Valorant")
                .description("The good FPS game.")
                .location("CMU")
                .date("July 25, 2022")
                .time("12:00")
                .petsAllowed(false)
                .organizer("Riot")
                .build());

        eventList.add(Event.builder()
                .id(456123L)
                .category("movie")
                .title("Arcane")
                .description("The good movie.")
                .location("CMU")
                .date("July 25, 2022")
                .time("12:00")
                .petsAllowed(false)
                .organizer("Riot")
                .build());
    }

    @GetMapping("events")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {
        perPage = perPage == null ? eventList.size() : perPage;
        page = page == null ? eventList.size() : page;
        Integer firstIndex = (page - 1) * perPage;
        List<Event> output = new ArrayList<>();

        try {
            for (int i = firstIndex; i < firstIndex + perPage && i < eventList.size(); i++) {
                output.add(eventList.get(i));
            }
            return ResponseEntity.ok(eventList);
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.ok(eventList);
        }
    }
}
