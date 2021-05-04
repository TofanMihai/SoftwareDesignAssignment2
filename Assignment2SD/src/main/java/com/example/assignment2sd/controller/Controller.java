package com.example.assignment2sd.controller;


import com.example.assignment2sd.bll.CovidTestBLL;
import com.example.assignment2sd.bll.EventBLL;
import com.example.assignment2sd.bll.EventBuilder;
import com.example.assignment2sd.bll.FighterBLL;
import com.example.assignment2sd.objects.CovidTest;
import com.example.assignment2sd.objects.Event;
import com.example.assignment2sd.objects.Fighter;
import com.example.assignment2sd.strategy.Context;
import com.example.assignment2sd.strategy.IStrategy;
import com.example.assignment2sd.strategy.MonthlyScheduleStrategy;
import com.example.assignment2sd.strategy.WeeklyScheduleStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@RequestMapping("/assignment2")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class Controller
{
    private final CovidTestBLL covidTestBLL;
    private final FighterBLL fighterBLL;
    private final EventBLL eventBLL;
    private Context context;
    private IStrategy strategy;


    @GetMapping(path="/covidtestsList")
    public List<CovidTest> getCovidTestList()
    {
        return covidTestBLL.getDAO().report();
    }

    @GetMapping(path="/fightersList")
    public List<Fighter> getFighterList()
    {
        return fighterBLL.getDAO().report();
    }

    @GetMapping(path = "/eventsList")
    public List<Event> getEventsList()
    {
        return eventBLL.getDAO().report();
    }

    @PostMapping(path="/covidtestsTable")
    public void createCovidTest(@RequestBody CovidTest covidTest)
    {
        covidTestBLL.insertCovidTest(covidTest);
    }

    @PostMapping(path="/fightersTable")
    public ResponseEntity createFighter(@RequestBody Fighter newFighter){
        try {
            fighterBLL.insertFighter(newFighter);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("You have successfully registered");
    }

    private String message = "There are currently no announcements. Check again later.";
    private String arrivalTestDate = "";

    @PostMapping(path="/sendInvite/{message}")
    private void sendInvite(@PathVariable String message){
        this.message = message;
    }

    @GetMapping(path="/getInvite")
    private String getInvite(){
        return message;
    }

    @PostMapping(path="/sendArrivalTestDate/{arrivalTestDate}")
    private void sendArrivalTestDate(@PathVariable String arrivalTestDate){
        this.arrivalTestDate = arrivalTestDate;
        System.out.println(arrivalTestDate);
    }

    @GetMapping(path="/getArrivalTestDate")
    private String getArrivalTestDate(){
        return arrivalTestDate;
    }

    public void resetWeek(){
        this.weekFlag = 0;
    }

    @PutMapping(path="/resetWeek")
    public ResponseEntity resetWeekFlag(){
        resetWeek();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path="/updateFighters/{startingDate}")
    private ResponseEntity testFighters(@PathVariable String startingDate)
    {
        Date date  = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(startingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date == null)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No starting date has been selected");
        }
        else
        {
            CovidTestBLL covidTestBLL = new CovidTestBLL();
            FighterBLL fighterBLL = new FighterBLL();
            List<Fighter> fightersList = fighterBLL.getDAO().report();
            ArrayList<String> fightersIsolationList = new ArrayList<String>();

            for(Fighter f : fightersList)
            {
                covidTestBLL.generateRandomTestResult(f);
                fightersIsolationList.add(f.getInIsolation());
            }

            if(!fightersIsolationList.contains("Yes"))
            {
                System.out.println(fightersIsolationList);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("All the fighters are ready. The tournament can begin");
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Three weeks tests completed");
    }

    public int getIntRange(int start, int finish)
    {
        Random rnd = new Random();
        int randomNumber = start + rnd.nextInt(finish + 1 - start);
        return randomNumber;

    }

    public Date getDateRange(Date startDate, Date finishDate)
    {
        long startMillis = startDate.getTime();
        long endMillis = finishDate.getTime();
        long randomMilis = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        Date randomDate = new Date(randomMilis);
        return randomDate;

    }

    public Date addDays(Date initialDate, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(initialDate);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    private int weekFlag = 0, maxFlag = 0;

    @PostMapping(path="/eventsTable/{startingDate}/{chosenStrategy}")
    public ResponseEntity<List<Event>> createEvent(@PathVariable String startingDate, @PathVariable String chosenStrategy){
        List<Event> eventList = null;
        EventBLL eventBLL = new EventBLL();
        CovidTestBLL covidTestBLL = new CovidTestBLL();
        Date date = null;
        try
        {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(startingDate);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        Date begginingDate = null;
        if(date == null)
            return new ResponseEntity<List<Event>>(eventList, HttpStatus.FORBIDDEN);
        else
        {

            FighterBLL fighterBLL = new FighterBLL();
            List<Fighter> fightersList = fighterBLL.getDAO().report();
            List<CovidTest> testsList = covidTestBLL.getDAO().report();
            ArrayList<String> secondDatesList = new ArrayList<String>();
            for(CovidTest ct : testsList)
                secondDatesList.add(ct.getSecondTestDate());
            Collections.sort(secondDatesList ,Collections.reverseOrder());
            try {
                begginingDate = new SimpleDateFormat("yyyy-MM-dd").parse(secondDatesList.get(0));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ArrayList<String> locations = new ArrayList<String>();

            locations.addAll(Arrays.asList(new String[] {"Toyota Center", "PNC Arena", "TD Garden" , "Royal Arena", "The Forum"}));
            ArrayList<String> times = new ArrayList<String>();
            times.addAll(Arrays.asList(new String[] {"20:00", "20:30", "21:00", "21:30", "22:00"}));

            if(chosenStrategy.equals("Monthly"))
                context = new Context(new MonthlyScheduleStrategy());
            else
                context = new Context(new WeeklyScheduleStrategy());
            weekFlag = context.executeStrategy(weekFlag);

            for(int i = 0; i < 3 ; i++)
            {
                Date eventDate = getDateRange(addDays(begginingDate, 7*(this.weekFlag - 1)), addDays(begginingDate, 7*this.weekFlag));
                String dateString = new SimpleDateFormat("yyyy-MM-dd").format(eventDate);

                EventBuilder builder = new EventBuilder();
                builder.addDate(dateString);
                builder.addLocation(locations);
                builder.addHour(times);
                builder.addFighterOne(fightersList);
                builder.addFighterTwo(fightersList);
                builder.addWeek(this.weekFlag);

                Event event = builder.build();

                if(event.getFighterOne() == null || event.getFighterTwo() == null )
                {
                    System.out.println("Fighter NULL");
                    return new ResponseEntity<List<Event>>(eventList, HttpStatus.FORBIDDEN);

                }
                else
                {
                    this.maxFlag = this.weekFlag;
                    eventBLL.getDAO().insert(event);
                    System.out.println("Event Inserted = " + event);
                }

            }

            eventList = eventBLL.getDAO().getEventsByWeek(this.weekFlag);
            return new ResponseEntity<List<Event>>(eventList, HttpStatus.ACCEPTED);
        }
    }

    @PostMapping(path="/eventsTable/previous/{startingDate}")
    private ResponseEntity<List<Event>> previousWeek(@PathVariable String startingDate)
    {
        if(context.getStrategy().getClass().equals(WeeklyScheduleStrategy.class))
        {
            if(this.weekFlag <= 1)
                this.weekFlag = 1;
            else
                this.weekFlag --;
        }
        else
        {
            if(this.weekFlag <= 4)
                this.weekFlag = 4;
            else
                this.weekFlag -=4;
        }

        System.out.println(this.weekFlag);
        System.out.println(this.maxFlag);

        List<Event> eventList = null;
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(startingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date == null)
            return new ResponseEntity<List<Event>>(eventList, HttpStatus.FORBIDDEN);
        else
        {
            EventBLL eventBLL = new EventBLL();
            eventList = eventBLL.getDAO().getEventsByWeek(this.weekFlag);
            return new ResponseEntity<List<Event>>(eventList, HttpStatus.ACCEPTED);
        }
    }

    @PostMapping(path="/eventsTable/next/{startingDate}")
    private ResponseEntity<List<Event>> nextWeek(@PathVariable String startingDate)
    {
        if(context.getStrategy().getClass().equals(WeeklyScheduleStrategy.class))
        {
            if(this.weekFlag >= maxFlag)
                this.weekFlag = maxFlag;
            else
                this.weekFlag ++;
        }
        else
        {

            if(this.weekFlag >= maxFlag)
                this.weekFlag = maxFlag;
            else
                this.weekFlag +=4;
        }

        System.out.println(weekFlag);
        System.out.println(maxFlag);

        List<Event> eventList = null;
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(startingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date == null)
            return new ResponseEntity<List<Event>>(eventList, HttpStatus.FORBIDDEN);
        else
        {
            EventBLL eventBLL = new EventBLL();
            eventList = eventBLL.getDAO().getEventsByWeek(this.weekFlag);
            return new ResponseEntity<List<Event>>(eventList, HttpStatus.ACCEPTED);
        }
    }


    // POST, GET, PUT

    //POST = CREAREA CHESTIILOR NOI = trimite datele la server ( dinspre front inspre back)
    // Front -> App -> Database
    // Pentru adaugare

    // GET = Se iau chestii din back si se trimit catre front

    // PUT = Nu se adauga chestii, se modifica chestiile existente
    // Front -> Backend


}
