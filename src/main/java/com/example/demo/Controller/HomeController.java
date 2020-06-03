package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class HomeController {
    //denne bruges primært til at bære employee id igennem hele programmet
        public static int MasterEmployee;
    // front page
    @Autowired
    MotorhomeService motorhomeService;
    @Autowired
    SizeService sizeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AddressService addressService;
    @Autowired
    ExtraService extraService;
    @Autowired
    EndedReservationService endedReservationService;
    @Autowired
    RentalOfficeService rentalOfficeService;
    @Autowired
    com.example.demo.Service.ReservationService reservationService;
    @Autowired
    CityService cityService;

//En ide til en måde at "logge ind" på
    @GetMapping("/Login")
    public String LoginPage(Model model){
        List<Employee> employeeList = employeeService.fetchAll();
        model.addAttribute("employees", employeeList);
        return "Home/Login";
    }
    @GetMapping("/Login/{id}")
    public String LoginPage(@PathVariable("id") int id){
        MasterEmployee = id;
        return "redirect:/";

    }
    @PostMapping("/Logout")
    public String Logout(){
        MasterEmployee = 0;
        return "redirect:/";
    }
    //Login slut

    @GetMapping("/")
    public String FrontPage(Model model) {
        model.addAttribute("MasterEmployee", MasterEmployee);
        if(MasterEmployee == 0){
            List<Employee> employeeList = employeeService.fetchAll();
            model.addAttribute("employees", employeeList);
            return "Home/Login";
        }else {
            return "Home/FrontPage";
        }
    }


    //front page slut

    // reserve start


    @GetMapping("/ReserveMotorhome")
    public String ReserveMotorhome(Model model) {
        List<Customer> customerList = customerService.fetchAll();
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        List<RentalOffice> rentalOfficeList = rentalOfficeService.fetchAll();
        //List<Extra> extraList = extraService.fetchAll();

        model.addAttribute("rentalOffices", rentalOfficeList);
        model.addAttribute("customers", customerList);
        model.addAttribute("motorhomes", motorhomeList);
       // model.addAttribute("extras", extraList);


        return "Home/Reserve/ReserveMotorhome";
    }

    @PostMapping("/ReserveMotorhome")
    public String ReserveMotorHome(@ModelAttribute Reservation reservation, @ModelAttribute Extra extra, @RequestParam("customer-id") int customerid, @RequestParam("motorhome-id") int motorhomeid, @RequestParam("rentalOffice-id") int rentalOfficeId){

        extra.setExtra_id(extraService.createID());
        extraService.add(extra);

        reservation.setExtra_id(extra.getExtra_id()); // Setter extra id i reservation til det extra id der lige er blevet skabt i add reservation.


        reservation.setEmployee_id(MasterEmployee); //Bruges til at bestemme hvilken employee der bruges
        reservation.setCustomer_id(customerid);
        reservation.setMotorHome_id(motorhomeid);
        reservation.setRental_office_id(rentalOfficeId);

        long millis=System.currentTimeMillis(); //Får milli sec gået siden 1970
        java.sql.Date date = new java.sql.Date(millis); //laver det om til SQL date obj
        reservation.setCreation_date(date);  //Adder datoen til objektet

        reservationService.add(reservation);
        return "redirect:/";
    }

    //reserve slut

    //create motor home start

    @GetMapping("/CreateMotorhome")
    public String CreateMotorhome(Model model, Motorhome motorhome, Size size) {
        return "Home/Create/CreateMotorhome";
    }

    @PostMapping("/CreateMotorhome")
    public String CreateMotorhome(@Valid @ModelAttribute Motorhome m, Errors errors, @ModelAttribute Size s) {
        if(errors.hasErrors()){
            return "Home/Create/CreateMotorhome";
        }else {
            m.setMotorHome_id(motorhomeService.createID()); //Bliver det ikke lavet inde i .add?
            s.setSize_id(sizeService.createID());
            sizeService.add(s);
            m.setSize_id(s.getSize_id());
            motorhomeService.add(m);
            return "redirect:/";
        }
    }
    //create motorhome slut

    //create customer start

    @GetMapping("/CreateCustomer")
    public String CreateCustomer(Model model) {
        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customer", customerList);
        return "Home/Create/CreateCustomer";
    }

    @PostMapping("/CreateCustomer")
    public String CreateCustomer(@ModelAttribute Customer c, @ModelAttribute Address a){
        a.setAddress_id(addressService.createID());
        c.setCustomer_id(customerService.createID());
        c.setAddress_id(a.getAddress_id());

        long millis=System.currentTimeMillis(); //Får milli sec gået siden 1970
        java.sql.Date date = new java.sql.Date(millis); //laver det om til SQL date obj
        c.setCreate_date(date);  //Adder datoen til objektet

        addressService.add(a);
        customerService.add(c);
        return "redirect:/";
    }
    //create customer slut

    //about start
    @GetMapping("/About")
    public String About(Model model) {
        List<Employee> employeeList = employeeService.fetchAll();
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        List<Customer> customerList = customerService.fetchAll();
        List<RentalOffice> rentalOfficeList = rentalOfficeService.fetchAll();
        List<Address> addressList = addressService.fetchAll();
        List<Address> ImportantAddressList = new ArrayList<>();
        int i = 0; //variabel til at tælle på i loopet
        for (RentalOffice r : rentalOfficeList) { //Et loop for at se om rental offices og adresse hører sammen
            for (Address a : addressList) {
                if (a.getAddress_id() == r.getAddress_id()) {
                    ImportantAddressList.add(a); //bliver tilføjet
                }
                i++;
            }
        }
        model.addAttribute("address", ImportantAddressList);
        model.addAttribute("customers", customerList);
        model.addAttribute("employees", employeeList);
        model.addAttribute("motorHomes", motorhomeList);
        model.addAttribute("rentalOffices", rentalOfficeList);


        return "Home/About/About";
    }
    //about slut

    //delete customer start
    @GetMapping("/DeleteCustomer")
    public String deleteCustomer(Model model){
        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customers", customerList);
        return "Home/Delete/DeleteCustomer";
    }
    @PostMapping("/DeleteCustomer")
    public String deleteCustomer(@RequestParam("delete-id") int delete){
        customerService.delete(delete);
        return "redirect:/";
    }


    // delete customer slut

    // update customer start

    @GetMapping("/UpdateCustomer")
    public String updateCustomer(Model model){
        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customers", customerList);
        return "Home/Update/UpdateCustomer";
    }

    @GetMapping("/UpdateCustomer2")
    public String updateCustomer2(@RequestParam("update-id") int id, Model model){
        List<Customer> customerList = customerService.fetchAll();
        for(int i = 0; i < customerList.size(); i++){
            if(id == customerList.get(i).getCustomer_id()){
                model.addAttribute("address", addressService.findById(customerList.get(i).getAddress_id()));
            }
        }

        model.addAttribute("customers", customerService.findById(id));
        return "Home/Update/UpdateCustomer2";
    }
    @PostMapping("/UpdateCustomer2")
    public String updateCustomer2(@ModelAttribute Customer customer, @ModelAttribute Address address){
        customerService.update(customer);
        addressService.update(address);
        return "redirect:/";
    }
    // update customer slut

    //create employee start

    @GetMapping("/CreateEmployee")
    public String CreateEmployee(Model model) {
        List<RentalOffice> rentalOfficeList = rentalOfficeService.fetchAll();
        model.addAttribute("rentalOffices", rentalOfficeList);
        return "Home/Create/CreateEmployee";
    }

    @PostMapping("/CreateEmployee")
    public String CreateEmployee(@ModelAttribute Employee e, @ModelAttribute Address a, @RequestParam("rentalOffice-id") int rentalOffice_Id) {
        e.setEmployee_status(1); //Sætter automatisk status til 1 da en ny medarbejder burde ALTID være aktiv
        a.setAddress_id(addressService.createID());  //Sætter den nye addresses ID
        e.setEmployee_id(employeeService.createID()); //Sætter den nye employees ID
        e.setAddress_id(a.getAddress_id());    //Sætter employee address id til at være tilsvarende den nye addresses id
        e.setRental_office_id(rentalOffice_Id);   //Sætter rental office id til valgt(user input) rental office
        addressService.add(a);   //som kalder addressRepo.add(a) som tilføjer dataen fra objektet til et table i SQL.
        employeeService.add(e); //Samme som overstående bare med employee.
        return "redirect:/";  //Redirecter til main siden
    }
    //create employee slut

    //delete employee start
    @GetMapping("/DeleteEmployee")
    public String deleteEmployee(Model model){
        List<Employee> employeeList = employeeService.fetchAll();
        model.addAttribute("employees", employeeList);
        return "Home/Delete/DeleteEmployee";
    }
    @PostMapping("/DeleteEmployee")
    public String deleteEmployee(@RequestParam("delete-id") int delete){
        employeeService.delete(delete);
        return "redirect:/";
    }


    // delete employee slut

    // update employee start

    @GetMapping("/UpdateEmployee")
    public String updateEmployee(Model model){
        List<Employee> employeeList = employeeService.fetchAll();
        model.addAttribute("employees", employeeList);
        return "Home/Update/UpdateEmployee";
    }

    @GetMapping("/UpdateEmployee2")
    public String updateEmployee2(@RequestParam("update-id") int id, Model model){
        List<Employee> employeeList = employeeService.fetchAll();
        for(int i = 0; i < employeeList.size(); i++){
            if(id == employeeList.get(i).getEmployee_id()){
                model.addAttribute("address", addressService.findById(employeeList.get(i).getAddress_id()));
            }
        }

        model.addAttribute("employees", employeeService.findById(id));
        return "Home/Update/UpdateEmployee2";
    }
    @PostMapping("/UpdateEmployee2")
    public String updateEmployee2(@ModelAttribute Employee employee, @ModelAttribute Address address){
        employeeService.update(employee);
        addressService.update(address);
        return "redirect:/";
    }
    // update employee slut

    //delete motorhome start
    @GetMapping("/DeleteMotorhome")
    public String deleteMotorhome(Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        model.addAttribute("motorHomes", motorhomeList);
        return "Home/Delete/DeleteMotorhome";
    }
    @PostMapping("/DeleteMotorhome")
    public String deleteMotorhome(@RequestParam("delete-id") int delete){
        motorhomeService.delete(delete);
        return "redirect:/";
    }

    // delete motorhome slut

    // update motorhome start

    @GetMapping("/UpdateMotorhome")
    public String updateMotorhome(Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        model.addAttribute("motorHomes", motorhomeList);
        return "Home/Update/UpdateMotorhome";
    }

    @GetMapping("/UpdateMotorhome2")
    public String updateMotorhome2(@RequestParam("update-id") int id, Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        for(int i = 0; i < motorhomeList.size(); i++){
            if(id == motorhomeList.get(i).getMotorHome_id()){
                model.addAttribute("sizes", sizeService.findByID(motorhomeList.get(i).getSize_id()));
            }
        }

        model.addAttribute("motorHomes", motorhomeService.findById(id));
        return "Home/Update/UpdateMotorhome2";
    }
    @PostMapping("/UpdateMotorhome2")
    public String updateMotorhome2(@ModelAttribute Motorhome motorhome, @ModelAttribute Size size){
        motorhomeService.update(motorhome);
        sizeService.update(size);
        return "redirect:/";
    }
    // update motorhome slut

    // view reservation slut
    @GetMapping("/ViewReservation")
    public String ViewReservation (Model model) {
        List<Reservation> ResList = reservationService.fetchAll();
        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customers", customerList);
        model.addAttribute("reservations", ResList);
        return "Home/Reserve/ViewReservation";
    }
    // view reservation slut

    // end reservation start
    @GetMapping("/endReservation/{id}")
    public String endReservation(@PathVariable("id") int id, @ModelAttribute EndedReservation e, Model model){

        e.setReservation_id(id); //Sætter det nye endReservation objects field Reservation_id, til at være den valgte reservations ID.

        long millis=System.currentTimeMillis(); //Får milli sec gået siden 1970
        java.sql.Date date = new java.sql.Date(millis); //laver det om til SQL date obj
        e.setBilling_date(date);  //Sætter Billing_date fielded i endReservation til nuværende dato.

        model.addAttribute("EndedReservation", e);
        return "Home/Create/CreateEndedReservation";

    }
    @PostMapping("/endReservation")
    public String createEndedReservation(@ModelAttribute EndedReservation e) throws FileNotFoundException {

        //Oprettelse af nødvendigte objekter.
        List<Employee> employeeList = employeeService.fetchAll();
        Reservation reservationObj = reservationService.findByID(e.getReservation_id());
        Motorhome motorhomeObj = motorhomeService.findById(reservationObj.getMotorHome_id());
        Extra extraObj = extraService.findByID(reservationObj.getExtra_id());
        Customer customerObj = customerService.findById(reservationObj.getCustomer_id());
        Address addressObj = addressService.findById(customerObj.getAddress_id());
        City cityObj = cityService.findById(addressObj.getZip_code());


        e.setRental_office_id(employeeList.get(MasterEmployee - 1).getRental_office_id());
        e.setCompleted(1); //sætter completed field til 1 hvilket er true

        //pricing midlertidigt her
        int totalPrice = 0;



        long diff = reservationObj.getEnd_date().getTime() - reservationObj.getStart_date().getTime(); //får antal millisekunder fra 1. jan 1970, fra reservation end date, og minuser det med antallet af millisekunder fra start date, det resterende bløb bliver gemt i diff, som er antallet af millisekunder mellem start og slut.
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;  //lånt fra internet - Her bliver antallet af millisekunder mellem de to overstående tidspunkter divideret med antallet af millisekunder på en dag, hvilket giver antallet af dage mellem de to tidspunkter.
        int daysBetween = (int) diffDays; //Parser long til int - og gør den positiv fra negativ.

        //opdaterer motorhomets odometer

        e.setKm_driven(e.getKm_driven() - motorhomeObj.getOdometer());  //Sætter km driven til at være det nye odometer - det gamle.
        motorhomeObj.setOdometer(motorhomeObj.getOdometer() + e.getKm_driven()); //Sætter motorhome objektets odometer til at være dets gamle odometer + de kørte km under udlejningen.
        motorhomeService.update(motorhomeObj); //Kalder motorhomeRepo update som opdaterer databasen


        //Tilføjer motorhomets pris per dag ganget med antallet af udlejnings dage.
        totalPrice += daysBetween * motorhomeObj.getPrice_per_day();
        totalPrice = (int) (totalPrice * Pricing.getSeasonFee(reservationObj)); //getSeasonFee returner en double som enten er 1.0, 1.3, eller 1.6 afhængigt af seasonen

        //Bedregning for km pr dag
        if(e.getKm_driven() / daysBetween > 400){
            totalPrice += e.getKm_driven() - daysBetween * 400; //Beregningen lægger antal overskredede km til totalprice, da det er 1€ pr 1km over.
        }

        totalPrice += Pricing.extrasPrice(extraObj, daysBetween); //Pricing metoden returner den samlede pris for alle extras pris per dag * udlejnings dage.


        totalPrice += e.getExtra_repair();  //Extra reparations omkostninger (user input)

        if(e.getFuel_tank() == 1){
            totalPrice += 70; //Koster 70€ hvis den er under halv
        }

        //Droppoint bliver lagt til totalPrice
        totalPrice += reservationObj.getDroppoint() * 0.70;
        totalPrice += e.getDroppoint() * 0.70;

        
        e.setTotal_price(totalPrice);  //Sætter det nye EndedReservation objekts total price field til det samlede beløb.
        //pricing slut
        Pricing.createReceipt(reservationObj, customerObj, extraObj, e, cityObj, motorhomeObj, addressObj, daysBetween);

        endedReservationService.add(e);
        reservationService.delete(e.getReservation_id()); //For at slette den reservation der lige er blevet afsluttet
        return "redirect:/";
    }
    // end reservation slut


    //view past reservations start
    @GetMapping("/ViewPastReservations")
    public String ViewPastReservations(Model model) {
        List<EndedReservation> endedReservationList = endedReservationService.fetchAll();
        model.addAttribute("endedreservations", endedReservationList);
        return "Home/Reserve/ViewPastReservations";
    }
    //view past reservations slut

    // cancel reservation start
    @GetMapping("/cancelReservation/{id}")
    public String CancelReservation(@ModelAttribute EndedReservation e, @PathVariable("id") int id){

        Reservation r = reservationService.findByID(id);
        Motorhome motorhomeObj = motorhomeService.findById(r.getMotorHome_id());
        Extra extraObj = extraService.findByID(r.getExtra_id());
        e.setEndedReservation_id(endedReservationService.createID());
        e.setCompleted(0); //0 hvilket er false
        e.setReservation_id(r.getReservation_id());
        e.setRental_office_id(r.getRental_office_id());
        
        int totalPrice = 0;

        long millis=System.currentTimeMillis(); //Får milli sec gået siden 1970
        java.sql.Date date = new java.sql.Date(millis); //laver det om til SQL date obj
        //Der skal udregnes hvor lang tid der er fra nu til reservations datoen for at bestemme hvor meget der skal slåes af prisen
        long howmuch = r.getStart_date().getTime() - millis;
        long howmuchdays = howmuch / (24 * 60 * 60 * 1000) + 1;
        int  tillStartdate = (int) howmuchdays; //For at bestemme hvor mange dage der er til startdatoen
        e.setBilling_date(date);

        long diff = r.getEnd_date().getTime() - r.getStart_date().getTime();//får antal millisekunder fra 1. jan 1970, fra reservation end date, og minuser det med antallet af millisekunder fra start date, det resterende bløb bliver gemt i diff, som er antallet af millisekunder mellem start og slut.
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;  //lånt fra internet - Her bliver antallet af millisekunder mellem de to overstående tidspunkter divideret med antallet af millisekunder på en dag, hvilket giver antallet af dage mellem de to tidspunkter.
        int daysBetween = (int) diffDays;  //Reservationens længde i dage med start og slut dato.

        totalPrice += daysBetween * motorhomeObj.getPrice_per_day();  //Sætter totalPrice til: totalPrice + reservertionens længde i dage * motorhomets pris pr dag.

        totalPrice += Pricing.extrasPrice(extraObj, daysBetween);  //Pricing metoden returner den samlede pris for alle extras pris per dag * udlejnings dage.

        totalPrice += r.getDroppoint() * 0.70;  // droppoint


        //Nu begyndes udregningen til hvilken slags cancel det er
        if(tillStartdate > 50 ){
            totalPrice = Pricing.totalPriceMinimum((int) (totalPrice * 0.2)); //tester om 20% af totalprice er over 200€
            e.setTotal_price(totalPrice);
        }else if(tillStartdate < 50 && tillStartdate > 15){
            totalPrice = Pricing.totalPriceMinimum((int) (totalPrice * 0.5)); //tester om 50% af totalprice er over 200€
            e.setTotal_price(totalPrice);
        }else if(tillStartdate <= 15 && tillStartdate > 1){
            totalPrice = Pricing.totalPriceMinimum((int) (totalPrice * 0.8)); //tester om 80% af totalprice er over 200€
            e.setTotal_price(totalPrice);
        }else if(tillStartdate == 1){
            totalPrice = Pricing.totalPriceMinimum((int) (totalPrice * 0.95)); //tester om 95% af totalprice er over 200€
            e.setTotal_price(totalPrice);
        }

        endedReservationService.add(e);
        reservationService.delete(r.getReservation_id());
        return "redirect:/";
    }
    // cancel reservation slut

    //create RentalOffice start

    @GetMapping("/CreateRentalOffice")
    public String CreateRentalOffice(Model model) {
        List<RentalOffice> rentalOfficeList = rentalOfficeService.fetchAll();
        model.addAttribute("rentalOffice", rentalOfficeList);
        return "Home/Create/CreateRentalOffice";
    }

    @PostMapping("/CreateRentalOffice")
    public String CreateRentalOffice(@ModelAttribute RentalOffice r, @ModelAttribute Address a){
        a.setAddress_id(addressService.createID());
        r.setRental_office_id(rentalOfficeService.createID());
        r.setAddress_id(a.getAddress_id());

        addressService.add(a);
        rentalOfficeService.add(r);
        return "redirect:/";
    }
    //create RentalOffice slut

    //delete rental office start

    @GetMapping("/DeleteRentalOffice")
    public String deleteRentalOffice(Model model) {
        List<RentalOffice> rentalOfficeList = rentalOfficeService.fetchAll();
        List<Address> addressList = addressService.fetchAll();
        List<Address> ImportantAddressList = new ArrayList<>();
        int i = 0; //variabel til at tælle på i loopet
        for (RentalOffice r : rentalOfficeList) { //Et loop for at se om rental offices og adresse hører sammen
            for (Address a : addressList) {
                if (a.getAddress_id() == r.getAddress_id()) {
                    ImportantAddressList.add(a); //bliver tilføjet
                }
                i++;
            }
        }
        model.addAttribute("address", ImportantAddressList);
        model.addAttribute("rentalOffices", rentalOfficeList);
        return "Home/Delete/DeleteRentalOffice";
    }
    @PostMapping("/DeleteRentalOffice")
    public String deleteRentalOffice(@RequestParam("delete-id") int delete){
        rentalOfficeService.delete(delete);
        return "redirect:/";
    }


    // delete rental office slut
}

