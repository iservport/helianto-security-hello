package org.helianto.sample.controller

import org.helianto.root.AuthorityExtractor
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/data"))
class HelloController extends AuthorityExtractor {

  @GetMapping
  def get(implicit oauth: OAuth2Authentication) = s"""{"userId":"${_userId}"}"""

}
