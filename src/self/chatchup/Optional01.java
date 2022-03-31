package self.chatchup;

import java.util.Optional;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public class Optional01 {

	private String aa = "";
	public String bb = "";

	public static void main(String[] args) {
		// https://qiita.com/rubytomato@github/items/92ac7944c830e54aa03d
		
		{
			Optional<String> str1 = Optional.empty();
			log.debug("Optional=empty");
			log.debug("Optional::empty");
			log.debug("Optional::isPresent :" + Boolean.toString(str1.isPresent()));
			log.debug("Optional::isEmpty :" + Boolean.toString(str1.isEmpty()));
			log.debug("--------------------\n");
		}

		{
			String val = "test";
			Optional<String> str = Optional.ofNullable(val);
			log.debug("Optional='test'");
			log.debug("Optional::ofNullable");
			log.debug("Optional::isPresent :" + Boolean.toString(str.isPresent()));
			log.debug("Optional::isEmpty :" + Boolean.toString(str.isEmpty()));
			log.debug("Optional::orElse :" + str.orElse("test2"));
			log.debug("Optional::orElseGet :" + str.orElseGet(() -> "test2"));
			val = null;
			str = Optional.ofNullable(val);
			log.debug("Optional=null");
			log.debug("Optional::ofNullable");
			log.debug("Optional::isPresent :" + Boolean.toString(str.isPresent()));
			log.debug("Optional::isEmpty :" + Boolean.toString(str.isEmpty()));
			log.debug("Optional::orElse :" + str.orElse("test2"));
			log.debug("Optional::orElseGet :" + str.orElseGet(() -> "test2"));
			log.debug("--------------------\n");

		}

		{
			String val = "test";
			Optional<String> str = Optional.of(val);
			log.debug("Optional='test'");
			log.debug("Optional::of");
			log.debug("Optional::isPresent :" + Boolean.toString(str.isPresent()));
			log.debug("Optional::isEmpty :" + Boolean.toString(str.isEmpty()));
			log.debug("Optional::get :" + str.get());

			log.debug("Optional=null");
			log.debug("Optional::of");
			val = null;
			try {
				str = Optional.of(val);
			} catch (NullPointerException e) {
				log.error(e);
			}
			log.debug("Optional::isPresent :" + Boolean.toString(str.isPresent()));
			log.debug("Optional::isEmpty :" + Boolean.toString(str.isEmpty()));
			log.debug("--------------------\n");
		}

		{
			Optional<String> str3 = Optional.ofNullable("test3");
			str3.ifPresent((s) -> {
				s = "test-test";
				log.debug("Optional::ifPresent :" + s);
			});
			Optional<String> str4 = Optional.ofNullable(null);
			str4.ifPresentOrElse((s) -> {
				s = "test-test";
				log.debug("Optional::ifPresentOrElse :" + s);
			}, () -> {
				log.debug("Optional::ifPresentOrElse :empty action");
			});
		}

	}

}
