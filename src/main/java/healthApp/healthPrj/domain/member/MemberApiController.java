package healthApp.healthPrj.domain.member;

import healthApp.healthPrj.domain.member.dto.CreateMemberRequest;
import healthApp.healthPrj.domain.member.dto.MemberDto;
import healthApp.healthPrj.common.dto.Result;
import healthApp.healthPrj.common.object.Address;
import healthApp.healthPrj.domain.member.dto.MemberSearch;
import healthApp.healthPrj.domain.member.model.Member;
import healthApp.healthPrj.domain.member.service.MemberService;
import healthApp.healthPrj.domain.member.service.query.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    /**
     * 회원가입
     * @param request
     * @return ResponseEntity<?>
     */
    @PostMapping("/join")
    public ResponseEntity<?> saveMember(@RequestBody @Valid CreateMemberRequest request){
        Address address = new Address(request.getCity(),request.getStreet(), request.getZipcode());
        Member member = new Member(request.getEmailId(), request.getPassword(), request.getMemberName(), request.getMemberAge(), request.getMemberSex(),address );
        memberService.join(member);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * 회원전체조회(이름 검색 기능)
     * @return ResponseEntity<?>
     * 헬스장 계정이 회원등록을 위해 회원전체목록을 조회하는 API(페이징 처리)
     */
    public ResponseEntity<?> findMemberByMemberSearch(MemberSearch memberSearch, Pageable pageable){

        return ResponseEntity.ok(memberQueryService.findByMemberSearch(memberSearch,pageable));

    }




}
