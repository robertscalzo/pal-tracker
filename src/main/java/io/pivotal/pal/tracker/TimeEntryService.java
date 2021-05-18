package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryService {
    public TimeEntryRepository timeEntryRepository;
    public TimeEntryService(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository=timeEntryRepository;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> temp=this.timeEntryRepository.list();
        return new ResponseEntity(temp, HttpStatus.OK);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry temp=this.timeEntryRepository.find(id);
        if(temp==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(temp, HttpStatus.OK);
        }
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry temp=this.timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(temp, HttpStatus.CREATED);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry temp=this.timeEntryRepository.update(id,timeEntry);
        if(temp==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(temp, HttpStatus.OK);
        }
    }
    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
         this.timeEntryRepository.delete(id);
         return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
