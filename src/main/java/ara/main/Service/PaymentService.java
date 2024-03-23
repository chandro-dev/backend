package ara.main.Service;
import ara.main.Entity.Payment;
import ara.main.Repositories.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    public ResponseEntity<String> register(Payment payment){
        try {
            if (paymentRepository.existsById(payment.getPaymentId())){

                return ResponseEntity.badRequest().body("Ya existe este pago");
            }
            paymentRepository.save(payment);
            return ResponseEntity.ok("El producto fue registrado con exito");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    public ResponseEntity<List<Payment>> getAll(){
        try{
            return ResponseEntity.ok(paymentRepository.findAll());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
}
