# MiPlatform-Spring-MyBatis

## Information

MiPlatform Service Sample of the Tobesoft Official Sample.


### Removed Licensed Library files

			<systemPath>${project.basedir}/src/main/webapp/WEB-INF/lib/ojdbc7-12.1.0.1.jar</systemPath>
			<systemPath>${project.basedir}/src/main/webapp/WEB-INF/lib/miplatform-bsb-3.10.jar</systemPath>
      
      
### Need Setting DB

#### in '/src/main/webapp/WEB-INF/spring/root-context.xml'

		<property name="url" value="jdbc:oracle:thin:@118.219.52.29:1521:XE" />
		<property name="username" value="jlabsystem" />
		<property name="password" value="1234" />
