package com.felipevilla.Workhub20;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.felipevilla.Workhub20.model.PermissionModel;
import com.felipevilla.Workhub20.model.RoleModel;
import com.felipevilla.Workhub20.model.UserModel;
import com.felipevilla.Workhub20.model.Enum.RoleEnum;
import com.felipevilla.Workhub20.repository.UserRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			/*CREATE PERMISSION */
			PermissionModel createPermission = PermissionModel.builder()
			.name("CREATE")
			.build();

			PermissionModel readPermission = PermissionModel.builder()
			.name("READ")
			.build();

			PermissionModel updatePermission = PermissionModel.builder()
			.name("UPDATE")
			.build();

			PermissionModel deletePermission = PermissionModel.builder()
			.name("DELETE")
			.build();

			PermissionModel refactorPermission = PermissionModel.builder()
			.name("REFACTOR")
			.build();

			/*CREATE ROLES */
			RoleModel roleAdmin = RoleModel.builder()
			.roleEnum(RoleEnum.ADMIN)
			.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
			.build();

			RoleModel roleUser = RoleModel.builder()
			.roleEnum(RoleEnum.USER)
			.permissionList(Set.of(createPermission, readPermission))
			.build();

			RoleModel roleInvited = RoleModel.builder()
			.roleEnum(RoleEnum.INVITED)
			.permissionList(Set.of(readPermission))
			.build();

			RoleModel roleDeveloper = RoleModel.builder()
			.roleEnum(RoleEnum.DEVELOPER)
			.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
			.build();

			/*CREATE USER */

			UserModel userFelipe = UserModel.builder()
			.userName("Felipe")
			.password("$2a$10$unrAuI4afYUr11eTnsU9yubQrPfj404cYmh9Nl/CHikadK4C0mbXq")
			.isEnabled(true)
			.accountNoExpired(true)
			.accountNoLocked(true)
			.credentialNoExpired(true)
			.roles(Set.of(roleAdmin))
			.build();

			UserModel userKaren = UserModel.builder()
			.userName("Karen")
			.password("$2a$10$unrAuI4afYUr11eTnsU9yubQrPfj404cYmh9Nl/CHikadK4C0mbXq")
			.isEnabled(true)
			.accountNoExpired(true)
			.accountNoLocked(true)
			.credentialNoExpired(true)
			.roles(Set.of(roleUser))
			.build();

			UserModel userOscar = UserModel.builder()
			.userName("Oscar")
			.password("$2a$10$unrAuI4afYUr11eTnsU9yubQrPfj404cYmh9Nl/CHikadK4C0mbXq")
			.isEnabled(true)
			.accountNoExpired(true)
			.accountNoLocked(true)
			.credentialNoExpired(true)
			.roles(Set.of(roleInvited))
			.build();

			UserModel userMaribel = UserModel.builder()
			.userName("Maribel")
			.password("$2a$10$unrAuI4afYUr11eTnsU9yubQrPfj404cYmh9Nl/CHikadK4C0mbXq")
			.isEnabled(true)
			.accountNoExpired(true)
			.accountNoLocked(true)
			.credentialNoExpired(true)
			.roles(Set.of(roleDeveloper))
			.build();

			userRepository.saveAll(List.of(userFelipe, userKaren, userOscar, userMaribel));

		};
	}

}
