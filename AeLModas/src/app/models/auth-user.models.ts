export class AuthUser {

  id = 0;
  login = '';
  email = '';
  senha = '';
  token = '';
  roles: string[] = [];

  constructor( id: number, login: string, email: string, senha: string,
    token: string, roles: string[] ) {
    this.id = id;
    this.login = login;
    this.email = email;
    this.senha = senha;
    this.token = token;
    this.roles = roles;
  }

  static fromJson(jsonData: any): AuthUser {
    return new AuthUser(
      jsonData.id,
      jsonData.login,
      jsonData.email,
      jsonData.senha,
      jsonData.token,
      jsonData.roles
    );
  }

  toJson(): any {
    return {
      id: this.id,
      login: this.login,
      email: this.email,
      senha: this.senha,
      token: this.token,
      roles: this.roles
    };
  }

}
