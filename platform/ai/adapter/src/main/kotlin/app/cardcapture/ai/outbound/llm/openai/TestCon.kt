package app.cardcapture.ai.outbound.llm.openai

import app.cardcapture.ai.port.inbound.AiTemplateDesignUseCase
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestCon(
    private val aiTemplateDesignUseCase: AiTemplateDesignUseCase
) {

    @GetMapping("/test/ai")
    fun test(){
        val backGroundimagepropt = "오른쪽 아래에 커피 아이콘을, 왼쪽아래에는 자판기 아이콘이 있으면 좋을것 같아, 글자룰 크게 만들어서 강조해줘!, 배경으로 커피자판기를 마시는 사람들이 있는 사진이 백그라운드에 있었으면 좋겠어"
        val nonbackGroundimagepropt = "오른쪽 아래에 커피 아이콘을, 왼쪽아래에는 자판기 아이콘이 있으면 좋을것 같아, 글자룰 크게 만들어서 강조해줘!,"

        val req = AiTemplateDesignCommand.ofOtThrow(
            llmModel = "GPT",
            imageModel = "nanobanana",
            purpose = "커피 자판기 홍보 목적",
            prompt = backGroundimagepropt,
            color = "#FFFFFF",
            texts = listOf("새로운 커피 자판기를 만나보세요!", "많은 커피를 저렴한 가격에!")
        )

        aiTemplateDesignUseCase.design(req)
    }

}
