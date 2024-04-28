package sell.selling.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sell.selling.domain.member.Member;
import sell.selling.domain.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password)
    {
        return memberRepository.findByLoginId(loginId)
                .filter(m->m.getPassword().equals(password))
                .orElse(null);
    }
}
