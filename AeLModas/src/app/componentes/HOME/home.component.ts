import { CommonModule } from '@angular/common';
import { AfterViewInit, Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CONTENTComponent } from "./content/content.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule, RouterModule,
    CONTENTComponent
],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HOMEComponent implements AfterViewInit {

  imagemLogo: string = 'assets/imagens/logo.png';
  imagemBanner: string = 'banner.jpg';

  constructor() { }

  /* Efeito de animação no nome da loja */
  ngAfterViewInit() {
    setTimeout(() => {
      const nomeLoja = document.querySelector('.nome-loja');
      if (nomeLoja) {
        nomeLoja.classList.add('aparecer'); // Ativa a animação
      }
    }, 500); // Pequeno delay para suavizar
  }

}
