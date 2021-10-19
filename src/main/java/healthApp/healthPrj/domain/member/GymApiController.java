package healthApp.healthPrj.domain.member;

import healthApp.healthPrj.domain.member.dto.GymForm;
import healthApp.healthPrj.domain.member.dto.GymSearchCondition;
import healthApp.healthPrj.domain.member.service.query.GymQueryService;
import healthApp.healthPrj.domain.member.repository.GymRepository;
import healthApp.healthPrj.domain.member.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gym")
public class GymApiController {

    private final GymService gymService;
    private final GymQueryService gymQueryService;

    /**
     * 헬스장가입
     * @return ResponseEntity<?>
     */

    @PostMapping("/join")
    public ResponseEntity<?> saveGym(@RequestBody @Valid GymForm gymForm){

        gymService.join(gymForm);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * 헬스장 목록 조회
     */
    @GetMapping
    public ResponseEntity<?> findGyms(GymSearchCondition gymSearchCondition, Pageable pageable){

        return ResponseEntity.ok(gymQueryService.findByGymSearchCondition(gymSearchCondition,pageable));

    }






}