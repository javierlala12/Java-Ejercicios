package examen1819.solucionMarta;

import java.io.Serializable;

public class Libro implements Serializable
{
	private String título;
	private String texto;

	public String getTítulo()
	{
		return título;
	}
	public void setTítulo(String título)
	{
		this.título = título;
	}
	public String getTexto()
	{
		return texto;
	}
	
	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((título == null) ? 0 : título.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (título == null)
		{
			if (other.título != null)
				return false;
		} else if (!título.equals(other.título))
			return false;
		return true;
	}



}
